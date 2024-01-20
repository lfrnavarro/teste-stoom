package br.com.stoom.store.business;

import br.com.stoom.store.business.interfaces.IBrandBO;
import br.com.stoom.store.exceptions.NotFoundException;
import br.com.stoom.store.model.Brand;
import br.com.stoom.store.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandBO implements IBrandBO {
    @Autowired
    private BrandRepository brandRepository;

    @Override
    public void enableBrand(Long id){
        Brand brandActual = brandRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Brand not found with ID: " + id));
        brandActual.setActive(true);
        brandRepository.save(brandActual);
    }

    @Override
    public void disableBrand(Long id){
        Brand brandActual = brandRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Brand not found with ID: " + id));
        brandActual.setActive(false);
        brandRepository.save(brandActual);
    }

}