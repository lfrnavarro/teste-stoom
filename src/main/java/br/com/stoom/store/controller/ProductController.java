package br.com.stoom.store.controller;

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
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductBO productService;

    @GetMapping(value = "/")
    public ResponseEntity<List<ProductDTOResponse>> findAll() {
        List<ProductDTOResponse> p = productService.findAll();
        if(!p.isEmpty())
            return new ResponseEntity<>(p, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/description/{description}")
    public ResponseEntity<List<ProductDTOResponse>> findByDescription(@PathVariable String description) {
        List<ProductDTOResponse> p = productService.findByDescription(description);
        if(!p.isEmpty())
            return new ResponseEntity<>(p, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/brand/{brand}")
    public ResponseEntity<List<ProductDTOResponse>> findByBrand(@PathVariable String brand) {
        List<ProductDTOResponse> p = productService.findByBrand(brand);
        if(!p.isEmpty())
            return new ResponseEntity<>(p, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/category/{category}")
    public ResponseEntity<List<ProductDTOResponse>> findByCategory(@PathVariable String category) {
        List<ProductDTOResponse> p = productService.findByCategory(category);
        if(!p.isEmpty())
            return new ResponseEntity<>(p, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/")
    public ResponseEntity<String> save(@RequestBody @Valid ProductDTORequest productDTORequest) {
        try {
            productService.save(productDTORequest);
            return new ResponseEntity<>("Product saved successfully", HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>("Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody @Valid ProductDTORequest productDTORequest) {
        productService.update(id, productDTORequest);
        return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        productService.delete(id);
        return new ResponseEntity<>("Product deleted successfully", HttpStatus.NO_CONTENT);
    }

    @PatchMapping(value = "/enable/{id}")
    public ResponseEntity<String> enableProduct(@PathVariable Long id) {
        productService.enableProduct(id);
        return new ResponseEntity<>("Product enabled successfully", HttpStatus.OK);
    }

    @PatchMapping(value = "/disable/{id}")
    public ResponseEntity<String> disableProduct(@PathVariable Long id) {
        productService.disableProduct(id);
        return new ResponseEntity<>("Product disabled successfully", HttpStatus.OK);
    }

}
