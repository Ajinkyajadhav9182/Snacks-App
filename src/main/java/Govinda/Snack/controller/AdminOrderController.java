package Govinda.Snack.controller;

import Govinda.Snack.model.Order;
import Govinda.Snack.model.SnackProduct;
import Govinda.Snack.service.AdminOrderService;
import Govinda.Snack.service.SnackProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminOrderController {

    @Autowired
    private AdminOrderService service;

    @Autowired
    private SnackProductService productService;

    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return service.getAllOrders();
    }

    @GetMapping("/orders/{id}")
    public Order getOrderById(@PathVariable String id) {
        return service.getOrderById(id);
    }

    @PutMapping("/orders/{id}")
    public Order updateOrder(@PathVariable String id, @RequestBody Order order) {
        return service.updateOrder(id, order);
    }

    @PutMapping("/orders/{id}/deliver")
    public Order markAsDelivered(@PathVariable String id) {
        return service.markAsDelivered(id);
    }

    @DeleteMapping("/orders/{id}")
    public void deleteOrder(@PathVariable String id) {
        service.deleteOrder(id);
    }

    @PostMapping("/products")
    public SnackProduct createProduct(@Valid @RequestBody SnackProduct product) {
        return productService.createProduct(product);
    }

    @PutMapping("/products/{id}")
    public SnackProduct updateProduct(@PathVariable String id, @RequestBody SnackProduct product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable String id) {
        productService.deleteProduct(id);
    }

    @GetMapping("/products")
    public List<SnackProduct> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public SnackProduct getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }
}
