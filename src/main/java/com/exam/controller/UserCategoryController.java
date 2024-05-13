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
        System.out.println("ebd");
        System.out.println(c.getUser().getEmail());
        System.out.println(c.getUser().getId());
        return ResponseEntity.ok(this.userCategoryService.addUserCategory(c));
    }
//pending and paid
    @GetMapping("/{userId}")
    public List<UserCategory> getUserCategory(@PathVariable Long userId) {
        return this.userCategoryService.getUserCategory(userId);
    }
//paid
    @GetMapping(value="/", params = "userId")
    public List<UserCategory> getPaidUserCategory(@RequestParam Long userId) {
        System.out.println("ew c");
        return this.userCategoryService.getPaidUserCategory(userId);
    }

    @GetMapping(value="/all")
    public List<UserCategory> getPaidUserCategory(@RequestParam(required = false) Long cid, @RequestParam(defaultValue = "false") Boolean b, @RequestParam(defaultValue = "") String email) {
        return this.userCategoryService.getAllCategory(cid,b,email);
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
