package com.exam.service.impl;

import com.exam.model.UserCategory;
import com.exam.model.exam.Category;
import com.exam.repo.CategoryRepository;
import com.exam.repo.UserCategoryRepository;
import com.exam.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserCategoryRepository userCategoryRepository;

    @Override
    public Category addCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public Set<Category> getCategories(String searchText) {
        System.out.println(searchText);
        return new LinkedHashSet<>(this.categoryRepository.findByTitleContainingIgnoreCase(searchText));
    }

    @Override
    public Category getCategory(Long categoryId) {
        return this.categoryRepository.findById(categoryId).get();
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Category category = new Category();
        category.setCid(categoryId);
        this.categoryRepository.delete(category);
        //need to be fixed
        /*this.userCategoryRepository.findAllByCid(categoryId);
        List<UserCategory> list =  this.userCategoryRepository.findAllByCid(categoryId);
        System.out.println(list);*/
    }
}
