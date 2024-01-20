package br.com.stoom.store.controller;

import br.com.stoom.store.business.BrandBO;
import br.com.stoom.store.business.ProductBO;
import br.com.stoom.store.dto.request.ProductDTORequest;
import br.com.stoom.store.dto.response.ProductDTOResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    @Autowired
    private BrandBO brandService;

    @PatchMapping(value = "/enable/{id}")
    public ResponseEntity<String> enableBrand(@PathVariable Long id) {
        brandService.enableBrand(id);
        return new ResponseEntity<>("Brand enabled successfully", HttpStatus.OK);
    }

    @PatchMapping(value = "/disable/{id}")
    public ResponseEntity<String> disableBrand(@PathVariable Long id) {
        brandService.disableBrand(id);
        return new ResponseEntity<>("Brand disabled successfully", HttpStatus.OK);
    }

}
