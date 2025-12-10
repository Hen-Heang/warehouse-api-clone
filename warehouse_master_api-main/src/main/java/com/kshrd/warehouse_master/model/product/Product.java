package com.kshrd.warehouse_master.model.product;

import com.kshrd.warehouse_master.model.category.Category;
import com.kshrd.warehouse_master.model.category.CategoryResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    private Integer id;
    private String name;
    private Integer qty;
    private Double price;
    private String image;
    private String description;
    private Category category;
    private Boolean isPublish;
    private String createdDate;
    private String updatedDate;
}
