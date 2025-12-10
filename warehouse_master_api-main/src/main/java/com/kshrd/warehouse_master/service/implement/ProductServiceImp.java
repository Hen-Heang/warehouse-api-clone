package com.kshrd.warehouse_master.service.implement;

import com.kshrd.warehouse_master.exception.BadRequestException;
import com.kshrd.warehouse_master.exception.NoContentException;
import com.kshrd.warehouse_master.exception.AlreadyExistException;
import com.kshrd.warehouse_master.model.product.Product;
import com.kshrd.warehouse_master.model.product.ProductRequest;
import com.kshrd.warehouse_master.repository.ProductRepository;
import com.kshrd.warehouse_master.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImp implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product addNewProduct(ProductRequest productRequest)  {
        if (productRequest.getName().equals("string") || productRequest.getName().isBlank()){
            throw new BadRequestException("Can not use default value. Please input value! ");
        }
        Product checkForDuplicate = productRepository.getProductValueExist(productRequest.getName());
        if (checkForDuplicate!=null){
            throw new AlreadyExistException("This product is already exist ");
        }
        return productRepository.addNewProduct(productRequest);
    }
}
