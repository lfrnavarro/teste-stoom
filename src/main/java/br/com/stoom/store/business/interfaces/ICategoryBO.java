package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.model.Category;

public interface ICategoryBO {
    public Category enableCategory(Long id);

    public Category disableCategory(Long id);
}
