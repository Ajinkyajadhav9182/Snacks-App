package Govinda.Snack.service;

import Govinda.Snack.model.Order;
import Govinda.Snack.model.SnackItem;
import Govinda.Snack.model.SnackProduct;
import Govinda.Snack.model.dto.OrderDashboardDTO;
import Govinda.Snack.model.dto.OrderItemDTO;
import Govinda.Snack.repository.AdminOrderRepository;
import Govinda.Snack.repository.SnackProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminOrderServiceImpl implements AdminOrderService {

    @Autowired
    private AdminOrderRepository adminRepo;

    @Autowired
    private SnackProductRepository snackProductRepo;

    @Override
    public List<OrderDashboardDTO> getAllOrders() {
        return adminRepo.findAll().stream()
                .map(this::mapToDashboardDTO)
                .collect(Collectors.toList());
    }

    private OrderDashboardDTO mapToDashboardDTO(Order order) {
        // Convert SnackItem list to DTO list and calculate total
        List<OrderItemDTO> itemDTOs = order.getItems().stream()
                .map(item -> new OrderItemDTO(item.getSnackName(), item.getQuantity()))
                .collect(Collectors.toList());

        double totalAmount = order.getItems().stream()
                .mapToDouble(item -> getSnackPrice(item.getSnackName()) * item.getQuantity())
                .sum();

        return new OrderDashboardDTO(
                order.getId(),
                order.getCustomerName(),
                order.getEmail(),
                order.getContactNumber(),
                order.getAddress(),
                itemDTOs,
                totalAmount,
                order.isDelivered(),
                order.getCreatedAt()
        );
    }

    private double getSnackPrice(String snackName) {
        return snackProductRepo.findByNameIgnoreCase(snackName)
                .map(SnackProduct::getPrice)
                .orElse(0.0);
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
    public void deleteOrders(List<String> ids) {
        adminRepo.deleteAllById(ids);
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