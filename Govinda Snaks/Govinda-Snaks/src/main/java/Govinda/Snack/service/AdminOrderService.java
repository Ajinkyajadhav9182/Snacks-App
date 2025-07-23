package Govinda.Snack.service;

import Govinda.Snack.model.Order;
import java.util.List;

public interface AdminOrderService {
    List<Order> getAllOrders();
    Order getOrderById(String id);
    Order updateOrder(String id, Order updatedOrder);
    void deleteOrder(String id);
    Order markAsDelivered(String id);
}
