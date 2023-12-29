package org.csu.mypetstore.repository;

import org.csu.mypetstore.domain.Order;

import java.util.List;

public interface OrderDAO {
    List<Order> getOrdersByUsername(String username);
    List<Order> getOrdersByUsernameWithPage(String username, int page);
    Order getOrder(int orderId);
    int getRecordCount(String username);
    void insertOrder(Order order);

    void insertOrderStatus(Order order);
}
