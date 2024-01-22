package br.com.stoom.store.controller;

import br.com.stoom.store.business.BrandBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
