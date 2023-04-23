package com.exam.controller;

import com.exam.model.UserCategory;
import com.exam.model.UserHistory;
import com.exam.service.UserCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/my-category")
public class UserCategoryController {
    private UserCategoryService userCategoryService;

    @Autowired
    public UserCategoryController(UserCategoryService userCategoryService) {
        this.userCategoryService = userCategoryService;
    }

    @PostMapping("/")
    public ResponseEntity<UserCategory> addUserCategory(@RequestBody UserCategory h) {
        return ResponseEntity.ok(this.userCategoryService.addUserCategory(h));
    }


}
