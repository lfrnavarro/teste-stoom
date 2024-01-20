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
    public void enableCategory(Long id){
        Category categoryActual = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found with ID: " + id));
        categoryActual.setActive(true);
        categoryRepository.save(categoryActual);
    }

    @Override
    public void disableCategory(Long id){
        Category categoryActual = categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Category not found with ID: " + id));
        categoryActual.setActive(false);
        categoryRepository.save(categoryActual);
    }

}
