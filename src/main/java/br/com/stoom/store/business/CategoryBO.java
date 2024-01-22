package br.com.stoom.store.business;

import br.com.stoom.store.business.interfaces.ICategoryBO;
import br.com.stoom.store.exceptions.NotFoundException;
import br.com.stoom.store.model.Category;
import br.com.stoom.store.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryBO implements ICategoryBO {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category enableCategory(Long id){
        Category categoryActual = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found with ID: " + id));
        categoryActual.setActive(true);
        return categoryRepository.save(categoryActual);
    }

    @Override
    public Category disableCategory(Long id){
        Category categoryActual = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found with ID: " + id));
        categoryActual.setActive(false);
        return categoryRepository.save(categoryActual);
    }

}
