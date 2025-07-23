package Govinda.Snack.service;

import Govinda.Snack.model.Order;
import Govinda.Snack.repository.AdminOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminOrderServiceImpl implements AdminOrderService {

    @Autowired
    private AdminOrderRepository adminRepo;

    @Override
    public List<Order> getAllOrders() {
        return adminRepo.findAll();
    }

    @Override
    public Order getOrderById(String id) {
        return adminRepo.findById(id).orElse(null);
    }

    @Override
    public Order updateOrder(String id, Order updatedOrder) {
        if (adminRepo.existsById(id)) {
            updatedOrder.setId(id);
            return adminRepo.save(updatedOrder);
        }
        return null;
    }

    @Override
    public void deleteOrder(String id) {
        adminRepo.deleteById(id);
    }

    @Override
    public Order markAsDelivered(String id) {
        Order order = adminRepo.findById(id).orElse(null);
        if (order != null) {
            order.setDelivered(true);
            return adminRepo.save(order);
        }
        return null;
    }
}
