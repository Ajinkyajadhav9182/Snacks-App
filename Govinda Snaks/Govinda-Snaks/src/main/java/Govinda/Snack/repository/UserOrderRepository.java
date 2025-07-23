package Govinda.Snack.repository;

import Govinda.Snack.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserOrderRepository extends JpaRepository<Order, String> {
    Optional<Order> findByIdAndEmail(String id, String email);
    List<Order> findByEmailOrderByCreatedAtDesc(String email);
}
