package com.kshrd.warehouse_master.model.order;

import com.kshrd.warehouse_master.model.product.ProductOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    private Order order;
    private List<ProductOrder> products;
}
