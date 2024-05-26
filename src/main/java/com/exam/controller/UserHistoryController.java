package com.exam.controller;

import com.exam.model.User;
import com.exam.model.UserHistory;
import com.exam.service.UserHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/history")
public class UserHistoryController {
    @Autowired
    private UserHistoryService historyService;

    @Autowired
    public UserHistoryController(UserHistoryService historyService) {
        this.historyService = historyService;
    }

    @PostMapping("/")
    public ResponseEntity<UserHistory> save(@RequestBody UserHistory h) {
        return ResponseEntity.ok(this.historyService.saveHistory(h));
    }

    @GetMapping("/{id}")
    public List<UserHistory> getUserHistory(@PathVariable("id") Long id, @RequestParam(defaultValue = "0") int pageNumber) {
        User user = new User();
        user.setId(id);
        return this.historyService.getUserHistory(user,pageNumber);
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public List<UserHistory> getQuizAttempts(@RequestParam Long qid, @RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "") String searchText1, @RequestParam(defaultValue = "") String searchText2) {

        return this.historyService.getQuizAttempts(qid,pageNumber,searchText1,searchText2);
    }

    @GetMapping("/specific/")
    public List<UserHistory> getUserSpecificHistory(@RequestParam String category, @RequestParam Long userId, @RequestParam(defaultValue = "0") int pageNumber) {
        System.out.println(category);
        System.out.println(userId);
        User user = new User();
        user.setId(userId);
        return this.historyService.getUserSpecificHistory(category,user,pageNumber);
    }

}
