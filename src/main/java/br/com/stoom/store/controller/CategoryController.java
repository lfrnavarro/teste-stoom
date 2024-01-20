package br.com.stoom.store.controller;

import br.com.stoom.store.business.CategoryBO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryBO categoryService;

    @PatchMapping(value = "/enable/{id}")
    public ResponseEntity<String> enableCategory(@PathVariable Long id) {
        categoryService.enableCategory(id);
        return new ResponseEntity<>("Category enabled successfully", HttpStatus.OK);
    }

    @PatchMapping(value = "/disable/{id}")
    public ResponseEntity<String> disableCategory(@PathVariable Long id) {
        categoryService.disableCategory(id);
        return new ResponseEntity<>("Category disabled successfully", HttpStatus.OK);
    }

}
