package Govinda.Snack.service;

import Govinda.Snack.model.SnackProduct;

import java.util.List;

public interface SnackProductService {
    SnackProduct createProduct(SnackProduct product);
    SnackProduct updateProduct(String id, SnackProduct product);
    void deleteProduct(String id);
    List<SnackProduct> getAllProducts();
    SnackProduct getProductById(String id);
}
