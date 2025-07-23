package Govinda.Snack.repository;

import Govinda.Snack.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminOrderRepository extends JpaRepository<Order, String> {
}
