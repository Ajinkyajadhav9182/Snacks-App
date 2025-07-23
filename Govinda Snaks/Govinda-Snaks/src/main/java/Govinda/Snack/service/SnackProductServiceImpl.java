package Govinda.Snack.service;

import Govinda.Snack.model.SnackProduct;
import Govinda.Snack.repository.SnackProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SnackProductServiceImpl implements SnackProductService {

    @Autowired
    private SnackProductRepository repository;

    @Override
    public SnackProduct createProduct(SnackProduct product) {
        return repository.save(product);
    }

    @Override
    public SnackProduct updateProduct(String id, SnackProduct product) {
        SnackProduct existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        existing.setName(product.getName());
        existing.setPrice(product.getPrice());
        return repository.save(existing);
    }

    @Override
    public void deleteProduct(String id) {
        repository.deleteById(id);
    }

    @Override
    public List<SnackProduct> getAllProducts() {
        return repository.findAll();
    }

    @Override
    public SnackProduct getProductById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }
}
