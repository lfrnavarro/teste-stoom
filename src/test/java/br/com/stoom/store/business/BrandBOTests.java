package br.com.stoom.store.business;

import br.com.stoom.store.exceptions.NotFoundException;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.repository.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class BrandBOTests {

    @Mock
    private BrandRepository brandRepository;

    @InjectMocks
    private BrandBO brandBO = new BrandBO();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testEnableBrand() {
        Long brandId = 1L;
        Brand brand = createMockBrand();
        when(brandRepository.findById(brandId)).thenReturn(Optional.of(brand));
        when(brandRepository.save(brand)).thenReturn(brand);

        Brand enableBrand = brandBO.enableBrand(brandId);

        assertTrue(enableBrand.getActive());
    }

    @Test
    void testEnableBrandNotFound() {
        Long brandId = 1L;
        when(brandRepository.findById(brandId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> brandBO.enableBrand(brandId));
    }

    @Test
    void testDisableBrand() {
        Long brandId = 1L;
        Brand brand = createMockBrand();
        when(brandRepository.findById(brandId)).thenReturn(Optional.of(brand));
        when(brandRepository.save(brand)).thenReturn(brand);

        Brand disableBrand = brandBO.disableBrand(brandId);

        assertTrue(!disableBrand.getActive());
    }

    @Test
    void testDisableBrandNotFound() {
        Long brandId = 1L;
        when(brandRepository.findById(brandId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> brandBO.disableBrand(brandId));
    }

    private Brand createMockBrand() {
        Brand brand = new Brand();
        brand.setId(1L);
        brand.setDescription("Apple");
        brand.setActive(true);
        return brand;
    }
}
