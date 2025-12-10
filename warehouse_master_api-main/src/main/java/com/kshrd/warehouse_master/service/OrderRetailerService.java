package com.kshrd.warehouse_master.service;

import com.kshrd.warehouse_master.model.Cart.Cart;
import com.kshrd.warehouse_master.model.Cart.CartOrder;
import com.kshrd.warehouse_master.model.Cart.CartSummery;
import com.kshrd.warehouse_master.model.invoice.Invoice;
import com.kshrd.warehouse_master.model.order.Order;
import com.kshrd.warehouse_master.model.order.OrderDetail;
import com.kshrd.warehouse_master.model.product.ProductOrder;

import java.text.ParseException;
import java.util.List;

public interface OrderRetailerService {

    List<ProductOrder> addProductToCart(Integer storeId, List<CartOrder> orders);

    String removeProductFromCart(Integer productId);

    ProductOrder updateProductInCart(Integer productId, Integer qty);

    Cart viewCartDetail(Integer pageNumber, Integer pageSize) throws ParseException;

    Integer getTotalPage(Integer pageSize);

    String cancelCart();

    String saveToDraft();

    String confirmOrder();

    List<Order> getOrderActivities(String sort, Integer pageNumber, Integer pageSize) throws ParseException;

    Integer getTotalOrderPage(Integer pageSize);

    String confirmTransaction(Integer id);

    Invoice viewInvoiceByOrderId(Integer id) throws ParseException;

    OrderDetail getOrderDetailByOrderId(Integer id) throws ParseException;

    List<CartSummery> viewAllCarts();

    String markOrderAsArrived(Integer id);
}
