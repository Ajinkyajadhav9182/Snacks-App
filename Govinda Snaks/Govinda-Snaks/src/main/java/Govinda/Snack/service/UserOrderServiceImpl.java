package Govinda.Snack.service;

import Govinda.Snack.exception.ResourceNotFoundException;
import Govinda.Snack.model.Order;
import Govinda.Snack.model.SnackItem;
import Govinda.Snack.model.SnackProduct;
import Govinda.Snack.model.dto.OrderResponseDto;
import Govinda.Snack.repository.SnackProductRepository;
import Govinda.Snack.repository.UserOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class UserOrderServiceImpl implements UserOrderService {

    @Autowired
    private UserOrderRepository userRepo;

    @Autowired
    private SnackProductRepository productRepo;


    @Override
    public OrderResponseDto createOrder(Order order) {
        order.setCreatedAt(LocalDateTime.now());

        AtomicReference<BigDecimal> total = new AtomicReference<>(BigDecimal.ZERO);

        for (var item : order.getItems()) {
            productRepo.findByNameIgnoreCase(item.getSnackName())
                    .ifPresent(product -> {
                        BigDecimal itemTotal = BigDecimal.valueOf(product.getPrice())
                                .multiply(BigDecimal.valueOf(item.getQuantity()));
                        total.updateAndGet(current -> current.add(itemTotal));
                    });
        }

        BigDecimal roundedTotal = total.get().setScale(2, RoundingMode.HALF_UP);
        userRepo.save(order);

        return new OrderResponseDto("Order placed successfully", roundedTotal.doubleValue());
    }

    @Override
    public List<Order> getOrdersByEmail(String email) {
        return userRepo.findByEmailOrderByCreatedAtDesc(email);
    }

    @Override
    public String updateUserOrder(String id, Order updatedOrder, String email) {
        Order existing = userRepo.findById(id)
                .filter(order -> order.getEmail().equals(email))
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for email: " + email));

        updatedOrder.setId(id);
        updatedOrder.setCreatedAt(existing.getCreatedAt());
        Order saved = userRepo.save(updatedOrder);

        double totalPrice = calculateTotalPrice(saved.getItems());
        return "Order updated successfully. Total price: ₹" + totalPrice;
    }

    @Override
    public void deleteUserOrder(String id, String email) {
        Order existing = userRepo.findById(id)
                .filter(order -> order.getEmail().equals(email))
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for email: " + email));

        userRepo.deleteById(id);
    }

    @Override
    public List<SnackProduct> getAllSnackProducts() {
        return productRepo.findAll();
    }

    private double calculateTotalPrice(List<SnackItem> items) {
        double total = 0;
        for (SnackItem item : items) {
            SnackProduct product = productRepo.findByNameIgnoreCase(item.getSnackName())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + item.getSnackName()));
            total += product.getPrice() * item.getQuantity();
        }
        return total;
    }
}