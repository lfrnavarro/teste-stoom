package br.com.stoom.store.business.interfaces;

import br.com.stoom.store.dto.request.ProductDTORequest;
import br.com.stoom.store.dto.response.ProductDTOResponse;

import java.util.List;

public interface IBrandBO {
    public void enableBrand(Long id);

    public void disableBrand(Long id);
}
