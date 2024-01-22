package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.dto.request.ProductDTORequest;
import br.com.stoom.store.dto.response.ProductDTOResponse;
import br.com.stoom.store.model.Product;

import java.util.List;

public interface IProductBO {

    public List<ProductDTOResponse> findAll();

    public Product save(ProductDTORequest productDTORequest);

    public Product update(Long id, ProductDTORequest productDTORequest);

    public void delete(Long id);

    public List<ProductDTOResponse> findByDescription(String description);

    public List<ProductDTOResponse> findByBrand(String brand);

    public List<ProductDTOResponse> findByCategory(String category);

    public Product enableProduct(Long id);

    public Product disableProduct(Long id);
}
