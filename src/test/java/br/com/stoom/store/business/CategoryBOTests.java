package br.com.stoom.store.business;

import br.com.stoom.store.exceptions.NotFoundException;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class CategoryBOTests {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryBO categoryBO = new CategoryBO();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testEnableCategory() {
        Long categoryId = 1L;
        Category category = createMockCategory();
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        when(categoryRepository.save(category)).thenReturn(category);

        Category enableCategory = categoryBO.enableCategory(categoryId);

        assertTrue(enableCategory.getActive());
    }

    @Test
    void testEnableCategoryNotFound() {
        Long categoryId = 1L;
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> categoryBO.enableCategory(categoryId));
    }

    @Test
    void testDisableCategory() {
        Long categoryId = 1L;
        Category category = createMockCategory();
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        when(categoryRepository.save(category)).thenReturn(category);

        Category disableCategory = categoryBO.disableCategory(categoryId);

        assertTrue(!disableCategory.getActive());
    }

    @Test
    void testDisableCategoryNotFound() {
        Long categoryId = 1L;
        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> categoryBO.disableCategory(categoryId));
    }

    private Category createMockCategory() {
        Category category = new Category();
        category.setId(1L);
        category.setDescription("Smartphone");
        category.setActive(true);
        return category;
    }
}
