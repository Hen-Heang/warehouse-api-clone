package com.kshrd.warehouse_master.service;

import com.kshrd.warehouse_master.model.product.Product;
import com.kshrd.warehouse_master.model.product.ProductRequest;

import java.rmi.AlreadyBoundException;

public interface ProductService {

    Product addNewProduct(ProductRequest productRequest) throws AlreadyBoundException;
}
