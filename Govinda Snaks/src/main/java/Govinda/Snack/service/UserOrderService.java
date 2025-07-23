package Govinda.Snack.service;

import Govinda.Snack.model.Order;
import Govinda.Snack.model.SnackProduct;
import Govinda.Snack.model.dto.OrderResponseDto;

import java.util.List;
public interface UserOrderService {
    OrderResponseDto createOrder(Order order);
    List<Order> getOrdersByEmail(String email);
    String updateUserOrder(String id, Order updatedOrder, String email);
    void deleteUserOrder(String id, String email);
    List<SnackProduct> getAllSnackProducts();
}
