package com.kshrd.warehouse_master.model.history;

import com.kshrd.warehouse_master.model.order.Order;
import com.kshrd.warehouse_master.model.product.ProductOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailHistory {
    private OrderHistory order;
    private List<ProductOrder> products;
}
