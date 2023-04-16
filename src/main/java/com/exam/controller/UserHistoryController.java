package com.exam.controller;

import com.exam.model.User;
import com.exam.model.UserHistory;
import com.exam.service.UserHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public List<UserHistory> getUserHistory(@PathVariable("id") Long id) {
        User user = new User();
        user.setId(id);
        return this.historyService.getUserHistory(user);
    }

}
