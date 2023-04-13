package com.exam.service.impl;

import com.exam.model.UserHistory;
import com.exam.repo.UserHistoryRepository;
import com.exam.service.UserHistoryService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserHistoryServiceImpl implements UserHistoryService {
    private UserHistoryRepository userHistoryRepository;

    @Override
    public UserHistory saveHistory(UserHistory h) {
        return this.userHistoryRepository.save(h);
    }
}
