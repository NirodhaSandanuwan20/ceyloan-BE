package com.exam.service;

import com.exam.model.User;
import com.exam.model.UserHistory;

import java.util.List;


public interface UserHistoryService {
    public UserHistory saveHistory(UserHistory h);

    List<UserHistory> getUserHistory(User user, int pageNumber);
    List<UserHistory> getUserSpecificHistory(String category,User user, int pageNumber);

    List<UserHistory> getQuizAttempts(Long qid, int pageNumber, String searchText1, String searchText2);
}
