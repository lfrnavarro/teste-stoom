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
    public Brand enableBrand(Long id){
        Brand brandActual = brandRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Brand not found with ID: " + id));
        brandActual.setActive(true);
        return brandRepository.save(brandActual);
    }

    @Override
    public Brand disableBrand(Long id){
        Brand brandActual = brandRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Brand not found with ID: " + id));
        brandActual.setActive(false);
        return brandRepository.save(brandActual);
    }

}
