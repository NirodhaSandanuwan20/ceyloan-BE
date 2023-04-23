package com.exam.controller;

import com.exam.helper.UserCategoryFoundException;
import com.exam.helper.UserFoundException;
import com.exam.model.UserCategory;
import com.exam.model.UserHistory;
import com.exam.service.UserCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<UserCategory> addUserCategory(@RequestBody UserCategory c) throws UserCategoryFoundException {
        return ResponseEntity.ok(this.userCategoryService.addUserCategory(c));
    }

    @GetMapping("/{userId}")
    public List<UserCategory> getUserCategory(@PathVariable Long userId) {
        return this.userCategoryService.getUserCategory(userId);
    }

    @DeleteMapping("/")
    public void deleteSelectedUserCategory(@RequestParam Long userCategoryId) {

         this.userCategoryService.deleteSelectedUserCategory(userCategoryId);
    }



    @ExceptionHandler(UserCategoryFoundException.class)
    public ResponseEntity<?> exceptionHandler(UserCategoryFoundException ex) {
        return ResponseEntity.ok(ex.getMessage());
    }


}
