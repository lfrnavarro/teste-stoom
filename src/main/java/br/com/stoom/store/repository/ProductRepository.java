package br.com.stoom.store.repository;

import br.com.stoom.store.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public List<Product> findByActiveTrueAndBrandActiveTrueAndCategoriesActiveTrue();

    public List<Product> findByActiveTrueAndBrandActiveTrueAndCategoriesActiveTrueAndDescriptionContainingIgnoreCase(String description);

    public List<Product> findByActiveTrueAndBrandActiveTrueAndCategoriesActiveTrueAndBrandDescriptionContainingIgnoreCase(String brand);

    public List<Product> findByActiveTrueAndBrandActiveTrueAndCategoriesActiveTrueAndCategoriesDescriptionContainingIgnoreCase(String category);
}