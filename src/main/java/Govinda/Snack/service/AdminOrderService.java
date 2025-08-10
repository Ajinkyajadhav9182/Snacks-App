package Govinda.Snack.service;

import Govinda.Snack.model.Order;
import Govinda.Snack.model.dto.OrderDashboardDTO;

import java.util.List;

public interface AdminOrderService {
    List<OrderDashboardDTO> getAllOrders();
    Order getOrderById(String id);
    Order updateOrder(String id, Order updatedOrder);
    void deleteOrder(String id);
    Order markAsDelivered(String id);
}
