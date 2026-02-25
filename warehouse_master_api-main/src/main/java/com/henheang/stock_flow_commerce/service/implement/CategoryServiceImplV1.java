package com.henheang.stock_flow_commerce.service.implement;

import com.henheang.stock_flow_commerce.exception.*;
import com.henheang.stock_flow_commerce.model.appUser.AppUser;
import com.henheang.stock_flow_commerce.model.category.Category;
import com.henheang.stock_flow_commerce.model.category.CategoryRequest;
import com.henheang.stock_flow_commerce.repository.AppUserRepository;
import com.henheang.stock_flow_commerce.repository.CategoryRepository;
import com.henheang.stock_flow_commerce.service.CategoryService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;

@Service
public class CategoryServiceImplV1 implements CategoryService {

    private final CategoryRepository categoryRepository;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public CategoryServiceImplV1(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

//    public Boolean checkCategoryExist(String name) {
//        System.out.println(categoryRepository.checkIfExist(name));
//        return categoryRepository.checkIfExist(name);
//    }

    Integer getStoreIdByCurrentUserId(Integer userId) {
        return categoryRepository.getStoreIdByCurrentUserId(userId);
    }


//    @Override
//    public Category insertCategory(CategoryRequest categoryRequest) {
//        return createNewCategory(categoryRequest);
//    }


    @Override
    public Category insertCategory(CategoryRequest categoryRequest) {
        return categoryRepository.insertCategory(categoryRequest);
    }

    //    @Override
//    public Category insertCategory(CategoryRequest categoryRequest) {
//        if (categoryRequest.getName().equals("string") || categoryRequest.getName().isBlank()) {
//            throw new BadRequestException("Can not use default value. Please input value! ");
//        }
//        if (!(checkCategoryExist(categoryRequest.getName().toLowerCase().trim()))) {
//            String newCategoryID = categoryRepository.createNewCategory(categoryRequest.getName().toLowerCase().trim());
//            System.out.println(newCategoryID);
//            if (!Objects.equals(newCategoryID, "1")) {
//                throw new InternalServerErrorException("Insert category failed.");
//            }
//        } else {
//            throw new ConflictException("Already exist.");
//        }
//
//        // get store id by current user id
//        AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        Integer currentUserId = appUser.getId();
//        Integer storeId = categoryRepository.getStoreIdByCurrentUserId(currentUserId);
//        // get category id by category name
//        Integer categoryId = categoryRepository.getCategoryIdByName(categoryRequest.getName().toLowerCase().trim());
//        //check duplicate in tb_store_category
//        if (categoryRepository.checkStoreCategoryDuplicate(storeId, categoryId)) {
//            throw new ConflictException("This user already have this category");
//        }
//
//        // insert to store_category
//
//        Category category = categoryRepository.insertCategory(storeId, categoryId);
//        if (category == null) {
//            throw new InternalServerErrorException("Insert store category failed");
//        }
//
////        return category;
//    }
    @Override
    public List<Category> getAllCategory(Integer pageNumber, Integer pageSize) throws ParseException {
        AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer currentUserId = appUser.getId();
        // check if store is created
        if (!categoryRepository.storeIsExist(currentUserId)) {
            throw new NotFoundException("User have not created store. Please create store to proceed with category.");
        }
        Integer storeId = categoryRepository.getStoreIdByCurrentUserId(currentUserId);
        if (pageNumber <= 0 || pageSize <= 0) {
            throw new NotFoundException("Page number and size should be higher than 0.");
        }
        if (categoryRepository.getCategories(storeId) < 1){
            throw new BadRequestException("This store have no category. Please create new category.");
        }
        Integer totalPage = findTotalPage(storeId);
        Integer totalCategory = categoryRepository.findTotalCategory(storeId);
        List<Category> categories = categoryRepository.getAllCategory(storeId, pageNumber, pageSize);
        if (totalCategory < pageSize * pageNumber && categories.isEmpty()) {
            throw new NotFoundException("Out of range. Total page is " + totalPage);
        }
        for (Category category : categories) {
            category.setCreatedDate(formatter.format(formatter.parse(category.getCreatedDate())));
            category.setUpdatedDate(formatter.format(formatter.parse(category.getUpdatedDate())));
        }
        return categories;
    }

    @Override
    public Category getCategoryById(Integer id) throws ParseException {
        AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer currentUserId = appUser.getId();
        if (!categoryRepository.storeIsExist(currentUserId)) {
            throw new NotFoundException("User have not created store. Please create store to proceed with category.");
        }
        Integer storeId = categoryRepository.getStoreIdByCurrentUserId(currentUserId);
        Category category = categoryRepository.getCategoryById(id, storeId);
        if (category == null) {
            throw new NotFoundException("Category Not found");
        }
        category.setCreatedDate(formatter.format(formatter.parse(category.getCreatedDate())));
        category.setUpdatedDate(formatter.format(formatter.parse(category.getUpdatedDate())));
        return category;
    }

    @Override
    public String deleteCategory(Integer id) {
        AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer currentUserId = appUser.getId();
        if (!categoryRepository.storeIsExist(currentUserId)) {
            throw new NotFoundException("User have not created store. Please create store to proceed with category.");
        }
        Integer storeId = categoryRepository.getStoreIdByCurrentUserId(currentUserId);
        if (!categoryRepository.checkIfStoreCategoryDuplicate(storeId, id)) {
            throw new NotFoundException("Category not found.");
        }
        // move all product that have this category to 113 UNKNOWN
        if (categoryRepository.checkIfCategoryHaveProduct(storeId, id)) {
            categoryRepository.createNewStoreCategory(storeId, 113);
            String unknownCategoryId = categoryRepository.moveProductCategory(storeId, id);
            if (!Objects.equals(unknownCategoryId, "113")) {
                throw new InternalServerErrorException("Fail to change category.");
            }
        }
        String categoryId = "default";
        categoryId = categoryRepository.deleteCategory(id, storeId);
        if (Objects.equals(categoryId, "default")) {
            throw new InternalServerErrorException("Fail to delete category.");
        }
        if (!Objects.equals(categoryId, String.valueOf(id))) {
            throw new InternalServerErrorException("Catastrophic error!!! Delete wrong category during operation.");
        }
        return "Successfully deleted category : " + categoryRepository.getCategoryNameById(Integer.parseInt(categoryId));
    }

    //Validation of updating in category
    @Override
    public Category editCategory(String name, Integer id) throws ParseException {
        if (name.equalsIgnoreCase("UNKNOWN")){
            throw new BadRequestException("Can not change category to unknown");
        }
        if (name.equals("string") || name.isBlank()) {
            throw new DefaultValueException("Can not use default value. Please input legal value.");
        }
        AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer currentUserId = appUser.getId();
        if (!categoryRepository.storeIsExist(currentUserId)) {
            throw new NotFoundException("User have not created store. Please create store to proceed with category.");
        }
        name = name.trim().toLowerCase();
        Integer storeId = categoryRepository.getStoreIdByCurrentUserId(currentUserId);
        Integer categoryIdByName = categoryRepository.getCategoryIdByName(name);
        // check if id exist
        if (!categoryRepository.checkIfStoreCategoryDuplicate(storeId, id)) {
            throw new NotFoundException("This ID does not exist.");
        }
        if (categoryRepository.checkIfStoreCategoryDuplicate(storeId, categoryIdByName)){
            throw new ConflictException("Already have this category name in this store. Please check and edit using different name.");
        }
        Category category = new Category();
        // check if new category exist or not
        if (categoryRepository.checkDuplicateCategory(name)) {
            // if exist get new category id
            Integer categoryId = categoryRepository.getCategoryIdByName(name);
            category = categoryRepository.editCategory(categoryId, id, storeId);
        } else {
            Integer newCategoryId = categoryRepository.createNewCategory(name);
            category = categoryRepository.editCategory(newCategoryId, id, storeId);
        }
        // internal server error just in case database error
        if (category == null) {
            throw new InternalServerErrorException("Internal server error. Fail to update category.");
        }
        // move from old category to new category
        categoryRepository.replaceProductCategory(id, category.getId(), storeId);

        category.setCreatedDate(formatter.format(formatter.parse(category.getCreatedDate())));
        category.setUpdatedDate(formatter.format(formatter.parse(category.getUpdatedDate())));
        return category;
    }

//    @Override
//    public List<Product> getProductByCategoryById(Integer id) {
//        Category category = categoryRepository.getCategoryById(id);
//        if (category == null) {
//            throw new NotFoundException("Product Not found");
//        }
//        return categoryRepository.getProductByCategoryId(id);
//    }

//    @Override
//    public Category createCategoryStore(CategoryRequest categoryRequest) {
//        return categoryRepository.insertCategory(storeId, getCategoryById());
//    }

//    @Override
//    public Category createNewCategory(CategoryRequest categoryRequest) {
//        return categoryRepository.insertCategory(categoryRequest);
//    }

//    @Override
//    public Category createNewCategory(CategoryRequest categoryRequest) {
//        return categoryRepository.createNewCategory();
//    }


    @Override
    public Category createCategoryStore(String name) throws ParseException {

        AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer currentUserId = appUser.getId();
        if (name.isBlank()) {
            throw new BadRequestException("Invalid name insertion. Please input name.");
        }
        if (!categoryRepository.storeIsExist(currentUserId)) {
            throw new NotFoundException("User have not created store. Please create store to proceed with category.");
        }
        Integer storeId = categoryRepository.getStoreIdByCurrentUserId(currentUserId);
        // trim white space
        name = name.trim().toLowerCase();
        Category category = new Category();
        // if category is already created or haven't been created
        if (!categoryRepository.checkDuplicateCategory(name)) { // if not yet create, create new
            Integer newCategoryId = categoryRepository.createNewCategory(name);
            category = categoryRepository.createNewStoreCategory(storeId, newCategoryId);
        } else { // if already created, just get the id and insert
            Integer categoryId = categoryRepository.getCategoryIdByName(name);
            if (categoryRepository.checkIfStoreCategoryDuplicate(storeId, categoryId)) {
                throw new ConflictException("Fail to create category because store already created this category.");
            }
            category = categoryRepository.createNewStoreCategory(storeId, categoryId);
        }
        // if insert fail
        if (category == null) {
            throw new InternalServerErrorException("Fail to create category. Something went during the process.");
        }
        category.setCreatedDate(formatter.format(formatter.parse(category.getCreatedDate())));
        category.setUpdatedDate(formatter.format(formatter.parse(category.getUpdatedDate())));
        return category;
    }


    @Override
    public List<Category> getCategoryByCurrentUserId() {
        AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer currentUserId = appUser.getId();
        if (!categoryRepository.storeIsExist(currentUserId)) {
            throw new NotFoundException("User have not created store. Please create store to proceed with category.");
        }
        if (!categoryRepository.storeIsExist(currentUserId)) {
            throw new NotFoundException("User have not created store. Please create store to proceed with category.");
        }
        Integer storeId = getStoreIdByCurrentUserId(currentUserId);
        return categoryRepository.getCategoryByCurrentUserId(storeId);
    }

    @Override
    public Integer findTotalPage(Integer pageSize) {
        AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer currentUserId = appUser.getId();
        if (!categoryRepository.storeIsExist(currentUserId)) {
            throw new NotFoundException("User have not created store. Please create store to proceed with category.");
        }
        Integer storeId = categoryRepository.getStoreIdByCurrentUserId(currentUserId);
        Integer totalCategory = categoryRepository.findTotalCategory(storeId);
        int totalPage;
        if (totalCategory % pageSize == 0) {
            totalPage = totalCategory / pageSize;
        } else {
            totalPage = (Integer) (totalCategory / pageSize) + 1;
        }
        return totalPage;
    }

    @Override
    public List<Category> searchCategoryByName(String name, Integer pageNumber, Integer pageSize) throws ParseException {
        AppUser appUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer currentUserId = appUser.getId();
        if (!categoryRepository.storeIsExist(currentUserId)) {
            throw new NotFoundException("User have not created store. Please create store to proceed with category.");
        }
        Integer storeId = categoryRepository.getStoreIdByCurrentUserId(currentUserId);
        if (pageNumber <= 0 || pageSize <= 0) {
            throw new NotFoundException("Page number and size should be higher than 0.");
        }
        Integer totalPage = findTotalPage(storeId);
        Integer totalCategory = categoryRepository.findTotalCategory(storeId);
        List<Category> categories = categoryRepository.searchCategoryByName(name, storeId, pageNumber, pageSize);
        if (categories == null) {
            throw new NotFoundException("No Categories are found in database! Please create new category!");
        }
        if (totalCategory < pageSize * pageNumber && categories.isEmpty()) {
            throw new NotFoundException("Out of range. Total page is " + totalPage);
        }
        for (Category category : categories) {
            category.setCreatedDate(formatter.format(formatter.parse(category.getCreatedDate())));
            category.setUpdatedDate(formatter.format(formatter.parse(category.getUpdatedDate())));
        }
        return categories;
    }

}






