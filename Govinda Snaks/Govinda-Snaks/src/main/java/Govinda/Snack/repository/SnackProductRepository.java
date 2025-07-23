package Govinda.Snack.repository;

import Govinda.Snack.model.SnackProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SnackProductRepository extends JpaRepository<SnackProduct, String> {
    Optional<SnackProduct> findByNameIgnoreCase(String name);
}