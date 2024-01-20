package br.com.stoom.store.business;

import br.com.stoom.store.business.interfaces.IProductBO;
import br.com.stoom.store.dto.request.ProductDTORequest;
import br.com.stoom.store.dto.response.ProductDTOResponse;
import br.com.stoom.store.exceptions.NotFoundException;
import br.com.stoom.store.mappers.ProductMapper;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductBO implements IProductBO {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDTOResponse> findAll(){
        return productMapper.mapListToProductDTOResponseList(productRepository.findByActiveTrueAndBrandActiveTrueAndCategoriesActiveTrue());
    }

    @Override
    public void save(ProductDTORequest productDTORequest){
        Product product = productMapper.mapToProduct(productDTORequest);
        productRepository.save(product);
    }

    @Override
    public void update(Long id, ProductDTORequest productDTORequest){
        Product productActual = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found with ID: " + id));
        Product productUpdate = productMapper.mapToProduct(productDTORequest);
        productActual.setId(id);
        productActual.setCategories(productUpdate.getCategories());
        productActual.setActive(productUpdate.getActive());
        productActual.setDescription(productUpdate.getDescription());
        productActual.setPrice(productUpdate.getPrice());
        productActual.setBrand(productUpdate.getBrand());
        productRepository.save(productActual);
    }

    @Override
    public void delete(Long id){
        Product productActual = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found with ID: " + id));
        productRepository.delete(productActual);
    }

    @Override
    public List<ProductDTOResponse> findByDescription(String description){
        return productMapper.mapListToProductDTOResponseList(productRepository.findByActiveTrueAndBrandActiveTrueAndCategoriesActiveTrueAndDescriptionContainingIgnoreCase(description));
    }

    @Override
    public List<ProductDTOResponse> findByBrand(String brand){
        return productMapper.mapListToProductDTOResponseList(productRepository.findByActiveTrueAndBrandActiveTrueAndCategoriesActiveTrueAndBrandDescriptionContainingIgnoreCase(brand));
    }

    @Override
    public List<ProductDTOResponse> findByCategory(String category){
        return productMapper.mapListToProductDTOResponseList(productRepository.findByActiveTrueAndBrandActiveTrueAndCategoriesActiveTrueAndCategoriesDescriptionContainingIgnoreCase(category));
    }

    @Override
    public void enableProduct(Long id){
        Product productActual = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found with ID: " + id));
        productActual.setActive(true);
        productRepository.save(productActual);
    }

    @Override
    public void disableProduct(Long id){
        Product productActual = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found with ID: " + id));
        productActual.setActive(false);
        productRepository.save(productActual);
    }

}
