package com.exam.service;

import com.exam.helper.UserCategoryFoundException;
import com.exam.model.UserCategory;

import java.util.List;
import java.util.Optional;

public interface UserCategoryService {
    UserCategory addUserCategory(UserCategory userCategory) throws UserCategoryFoundException;
    List<UserCategory> getUserCategory(Long userId);

    void deleteSelectedUserCategory(Long userCategoryId);
}
