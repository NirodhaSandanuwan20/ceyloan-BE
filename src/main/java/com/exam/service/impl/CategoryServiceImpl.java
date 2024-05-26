package com.exam.service.impl;

import com.exam.model.UserCategory;
import com.exam.model.exam.Category;
import com.exam.repo.CategoryRepository;
import com.exam.repo.UserCategoryRepository;
import com.exam.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    public Set<Category> getNonePaidCategories(String searchText, Long userId) {
        // Get user's categories
        List<UserCategory> userCategories = this.userCategoryRepository.findAllByUser_Id(userId);

        // Get all categories based on the search text
        List<Category> allCategories = this.categoryRepository.findByTitleContainingIgnoreCase(searchText);

        // Create a set to store none-paid categories
        Set<Category> nonePaidCategories = new HashSet<>(allCategories);

        // Remove user's categories from none-paid categories
        for (UserCategory userCategory : userCategories) {
            nonePaidCategories.removeIf(category -> category.getCid().equals(userCategory.getCid()));
        }

        return nonePaidCategories;
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
