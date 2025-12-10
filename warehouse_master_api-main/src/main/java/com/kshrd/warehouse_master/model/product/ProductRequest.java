package com.kshrd.warehouse_master.model.product;

import com.kshrd.warehouse_master.model.category.CategoryRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductRequest {
    private String name;
    private Integer qty;
    private Double price;
    private String image;
    private String description;
    private Integer categoryId;
    private Boolean isPublish;
}