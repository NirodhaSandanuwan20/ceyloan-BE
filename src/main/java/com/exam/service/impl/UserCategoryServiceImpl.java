package com.exam.service.impl;

import com.exam.helper.UserCategoryFoundException;
import com.exam.model.UserCategory;
import com.exam.repo.UserCategoryRepository;
import com.exam.service.UserCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserCategoryServiceImpl implements UserCategoryService {
    private UserCategoryRepository userCategoryRepository;

    @Autowired
    public UserCategoryServiceImpl(UserCategoryRepository userCategoryRepository) {
        this.userCategoryRepository = userCategoryRepository;
    }


    @Override
    public UserCategory addUserCategory(UserCategory userCategory) throws UserCategoryFoundException {
        Optional<UserCategory> selectedCategory = this.userCategoryRepository.findByCidAndUser_Id(userCategory.getCid(),userCategory.getUser().getId());
        System.out.println(selectedCategory);
        if(selectedCategory.isEmpty()){
            return this.userCategoryRepository.save(userCategory);
        }else{
            throw new UserCategoryFoundException();
        }

    }

    @Override
    public List<UserCategory> getUserCategory(Long cid) {
        return this.userCategoryRepository.findAllByUser_Id(cid);
    }
}
