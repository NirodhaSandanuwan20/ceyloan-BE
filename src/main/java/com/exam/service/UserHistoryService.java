package com.exam.service;

import com.exam.model.User;
import com.exam.model.UserHistory;
import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserHistoryService {
    public UserHistory saveHistory(UserHistory h);

    List<UserHistory> getUserHistory(User user);
}
