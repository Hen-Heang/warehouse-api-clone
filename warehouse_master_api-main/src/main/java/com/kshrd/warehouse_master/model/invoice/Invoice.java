package com.kshrd.warehouse_master.model.invoice;

import com.kshrd.warehouse_master.model.order.Order;
import com.kshrd.warehouse_master.model.product.ProductOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Invoice {
    private Order order;
    private List<ProductOrder> products;
}
