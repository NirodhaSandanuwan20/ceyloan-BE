package com.exam.service.impl;

import com.exam.model.UserHistory;
import com.exam.repo.UserHistoryRepository;
import com.exam.service.UserHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserHistoryServiceImpl implements UserHistoryService {
    private UserHistoryRepository userHistoryRepository;
    @Autowired
    public UserHistoryServiceImpl(UserHistoryRepository userHistoryRepository) {
        this.userHistoryRepository = userHistoryRepository;
    }

    @Override
    public UserHistory saveHistory(UserHistory h) {
        System.out.println(h);
        return this.userHistoryRepository.save(h);
    }
}
