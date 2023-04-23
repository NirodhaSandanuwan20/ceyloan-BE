package com.exam.service.impl;

import com.exam.model.UserCategory;
import com.exam.repo.UserCategoryRepository;
import com.exam.repo.UserHistoryRepository;
import com.exam.service.UserCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class UserCategoryServiceImpl implements UserCategoryService {
    private UserCategoryRepository userCategoryRepository;

    @Autowired
    public UserCategoryServiceImpl(UserCategoryRepository userCategoryRepository) {
        this.userCategoryRepository = userCategoryRepository;
    }


    @Override
    public UserCategory addUserCategory(UserCategory userCategory) {
        return this.userCategoryRepository.save(userCategory);
    }
}
