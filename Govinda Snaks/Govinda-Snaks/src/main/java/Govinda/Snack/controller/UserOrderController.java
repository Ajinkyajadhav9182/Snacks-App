package Govinda.Snack.controller;

import Govinda.Snack.model.Order;
import Govinda.Snack.model.SnackProduct;
import Govinda.Snack.model.dto.OrderResponseDto;
import Govinda.Snack.service.UserOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user/orders")
public class UserOrderController {

    @Autowired
    private UserOrderService service;

    @PostMapping
    public OrderResponseDto createOrder(@Valid @RequestBody Order order) {
        return service.createOrder(order);
    }

    @GetMapping
    public List<Order> getOrdersByEmail(@RequestParam String email) {
        return service.getOrdersByEmail(email);
    }

    @PutMapping("/{id}")
    public String updateOrder(@PathVariable String id, @RequestParam String email, @RequestBody Order order) {
        return service.updateUserOrder(id, order, email);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable String id, @RequestParam String email) {
        service.deleteUserOrder(id, email);
    }

    @GetMapping("/products")
    public List<SnackProduct> getAllProducts() {
        return service.getAllSnackProducts();
    }
}

