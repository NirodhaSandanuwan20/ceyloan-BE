package com.exam.controller;

import com.exam.model.UserHistory;
import com.exam.service.UserHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/history")
public class UserHistoryController {
    @Autowired
    private  UserHistoryService historyService;

    @Autowired
    public UserHistoryController(UserHistoryService historyService) {
        this.historyService = historyService;
    }

    @PostMapping("/")
    public ResponseEntity<UserHistory> save(@RequestBody UserHistory h) {
        return ResponseEntity.ok(this.historyService.saveHistory(h));
    }
}
