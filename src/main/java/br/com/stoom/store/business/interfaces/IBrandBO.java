package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.model.Brand;

public interface IBrandBO {
    public Brand enableBrand(Long id);

    public Brand disableBrand(Long id);
}
