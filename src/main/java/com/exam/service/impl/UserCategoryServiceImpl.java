package com.exam.service.impl;

import com.exam.repo.UserHistoryRepository;
import com.exam.service.UserCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserCategoryServiceImpl implements UserCategoryService {
    private UserHistoryRepository userHistoryRepository;

    @Autowired
    public UserCategoryServiceImpl(UserHistoryRepository userHistoryRepository) {
        this.userHistoryRepository = userHistoryRepository;
    }




}
