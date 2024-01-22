package br.com.stoom.store.business;

import br.com.stoom.store.dto.request.ProductDTORequest;
import br.com.stoom.store.dto.response.ProductDTOResponse;
import br.com.stoom.store.exceptions.NotFoundException;
import br.com.stoom.store.mappers.ProductMapper;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.model.Product;
import br.com.stoom.store.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductBOTests {

    @Mock
    private ProductMapper productMapper;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductBO productBO = new ProductBO();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testFindAll() {
        List<Product> productList = Collections.singletonList(createMockProduct());
        when(productRepository.findByActiveTrueAndBrandActiveTrueAndCategoriesActiveTrue()).thenReturn(productList);

        List<ProductDTOResponse> expectedResponseList = Collections.singletonList(createMockProductDTOResponse());
        when(productMapper.mapListToProductDTOResponseList(productList)).thenReturn(expectedResponseList);

        List<ProductDTOResponse> actualResponseList = productBO.findAll();

        assertEquals(expectedResponseList, actualResponseList);
    }

    @Test
    void testSave() {
        ProductDTORequest productDTORequest = createMockProductDTORequest();
        Product product = createMockProduct();

        when(productMapper.mapToProduct(productDTORequest)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(product);

        Product savedProduct = productBO.save(productDTORequest);

        assertNotNull(savedProduct);
    }

    @Test
    void testUpdate() {
        Long productId = 1L;
        ProductDTORequest productDTORequest = createMockProductDTORequest();
        Product productUpdate = createMockProduct();
        when(productMapper.mapToProduct(productDTORequest)).thenReturn(productUpdate);
        when(productRepository.findById(productId)).thenReturn(Optional.of(productUpdate));
        when(productRepository.save(productUpdate)).thenReturn(productUpdate);

        Product updatedProduct = productBO.update(productId, productDTORequest);

        assertEquals(productId, updatedProduct.getId());
    }

    @Test
    void testUpdateProductNotFound() {
        Long productId = 1L;
        ProductDTORequest productDTORequest = createMockProductDTORequest();
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> productBO.update(productId, productDTORequest));
    }

    @Test
    void testDeleteProductFound() {
        Long productId = 1L;
        Product productActual = createMockProduct();
        when(productRepository.findById(productId)).thenReturn(Optional.of(productActual));

        productBO.delete(productId);

        verify(productRepository, times(1)).delete(productActual);
    }

    @Test
    void testDeleteProductNotFound() {
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> productBO.delete(productId));
    }

    @Test
    void testFindByDescription() {
        String description = "Iphone";
        List<Product> productList = Arrays.asList(createMockProduct());
        when(productRepository.findByActiveTrueAndBrandActiveTrueAndCategoriesActiveTrueAndDescriptionContainingIgnoreCase(description)).thenReturn(productList);

        List<ProductDTOResponse> expectedResponseList = Arrays.asList(createMockProductDTOResponse());
        when(productMapper.mapListToProductDTOResponseList(productList)).thenReturn(expectedResponseList);

        List<ProductDTOResponse> actualResponseList = productBO.findByDescription(description);

        assertEquals(expectedResponseList, actualResponseList);
    }

    @Test
    void testFindByBrand() {
        String brandDescription = "Apple";
        Brand brand = new Brand();
        brand.setId(1L);
        brand.setDescription(brandDescription);

        List<Product> productList = Arrays.asList(createMockProduct());
        when(productRepository.findByActiveTrueAndBrandActiveTrueAndCategoriesActiveTrueAndBrandDescriptionContainingIgnoreCase(brandDescription)).thenReturn(productList);

        List<ProductDTOResponse> expectedResponseList = Arrays.asList(createMockProductDTOResponse());
        when(productMapper.mapListToProductDTOResponseList(productList)).thenReturn(expectedResponseList);

        List<ProductDTOResponse> actualResponseList = productBO.findByBrand(brandDescription);

        assertEquals(expectedResponseList, actualResponseList);
    }

    @Test
    void testFindByCategory() {
        String categoryDescription = "Smartphone";
        Category category = new Category();
        category.setId(1L);
        category.setDescription(categoryDescription);

        List<Product> productList = Arrays.asList(createMockProduct());
        when(productRepository.findByActiveTrueAndBrandActiveTrueAndCategoriesActiveTrueAndCategoriesDescriptionContainingIgnoreCase(categoryDescription)).thenReturn(productList);

        List<ProductDTOResponse> expectedResponseList = Arrays.asList(createMockProductDTOResponse());
        when(productMapper.mapListToProductDTOResponseList(productList)).thenReturn(expectedResponseList);

        List<ProductDTOResponse> actualResponseList = productBO.findByCategory(categoryDescription);

        assertEquals(expectedResponseList, actualResponseList);
    }

    @Test
    void testEnableProduct() {
        Long productId = 1L;
        Product product = createMockProduct();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        Product enabledProduct = productBO.enableProduct(productId);

        assertTrue(enabledProduct.getActive());
    }

    @Test
    void testEnableProductNotFound() {
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> productBO.enableProduct(productId));
    }

    @Test
    void testDisableProduct() {
        Long productId = 1L;
        Product product = createMockProduct();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        Product disableProduct = productBO.disableProduct(productId);

        assertTrue(!disableProduct.getActive());
    }

    @Test
    void testDisableProductNotFound() {
        Long productId = 1L;
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> productBO.disableProduct(productId));
    }

    private Product createMockProduct() {
        Brand brand = new Brand();
        brand.setId(1L);
        brand.setDescription("Apple");
        brand.setActive(true);

        Category category = new Category();
        category.setId(1L);
        category.setDescription("Smartphone");
        category.setActive(true);

        Product product = new Product();
        product.setId(1L);
        product.setDescription("Iphone");
        product.setCategories(Collections.singletonList(category));
        product.setBrand(brand);
        product.setPrice(BigDecimal.TEN);
        product.setActive(true);
        return product;
    }

    private ProductDTOResponse createMockProductDTOResponse() {
        Brand brand = new Brand();
        brand.setId(1L);
        brand.setDescription("Apple");
        brand.setActive(true);

        Category category = new Category();
        category.setId(1L);
        category.setDescription("Smartphone");
        category.setActive(true);

        ProductDTOResponse response = new ProductDTOResponse();
        response.setId(1L);
        response.setDescription("Iphone");
        response.setCategories(Collections.singletonList(category));
        response.setBrand(brand);
        response.setPrice(BigDecimal.TEN);
        return response;
    }

    private ProductDTORequest createMockProductDTORequest() {
        ProductDTORequest request = new ProductDTORequest();
        request.setDescription("Iphone");
        request.setCategoryIds(Collections.singletonList(1L));
        request.setBrandId(1L);
        request.setPrice(BigDecimal.TEN);
        request.setActive(true);
        return request;
    }
}