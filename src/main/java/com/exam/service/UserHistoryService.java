package com.exam.service;

import com.exam.model.UserHistory;
import org.springframework.stereotype.Service;


public interface UserHistoryService {
    public UserHistory saveHistory(UserHistory h);
}
