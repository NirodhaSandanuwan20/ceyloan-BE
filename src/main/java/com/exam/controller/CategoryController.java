package com.exam.controller;

import com.exam.model.exam.Category;
import com.exam.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    //add category
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Category> addCategory(@RequestBody Category category) {
        Category category1 = this.categoryService.addCategory(category);

        return ResponseEntity.ok(category1);
    }

    //get category
    @GetMapping("/{categoryId}")
    public Category getCategory(@PathVariable("categoryId") Long categoryId) {
        return this.categoryService.getCategory(categoryId);
    }

    //get all categories for none logged users
    @GetMapping("/all")
    public ResponseEntity<?> getCategories(@RequestParam(defaultValue = "") String searchText) {
        return ResponseEntity.ok(this.categoryService.getCategories(searchText));
    }

    //get none paid categories for logged users
    @GetMapping("/none-paid-category")
    public ResponseEntity<?> nonePaidCategories(@RequestParam(defaultValue = "") String searchText , @RequestParam Long userId) {
        return ResponseEntity.ok(this.categoryService.getNonePaidCategories(searchText , userId));
    }

    //update category
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/")
    public Category updateCategory(@RequestBody Category category) {
        return this.categoryService.updateCategory(category);
    }

    //delete category
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") Long categoryId) {
        this.categoryService.deleteCategory(categoryId);
    }

}
