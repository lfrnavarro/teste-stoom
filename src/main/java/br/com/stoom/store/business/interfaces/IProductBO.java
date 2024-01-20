package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.dto.request.ProductDTORequest;
import br.com.stoom.store.dto.response.ProductDTOResponse;
import br.com.stoom.store.model.Product;

import java.util.List;

public interface IProductBO {

    List<ProductDTOResponse> findAll();

    void save(ProductDTORequest productDTORequest);

    void update(Long id, ProductDTORequest productDTORequest);

    void delete(Long id);

    public List<ProductDTOResponse> findByDescription(String description);

    public List<ProductDTOResponse> findByBrand(String brand);

    public List<ProductDTOResponse> findByCategory(String category);

    public void enableProduct(Long id);

    public void disableProduct(Long id);
}
