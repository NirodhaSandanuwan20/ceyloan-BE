package com.exam.service.impl;

import com.exam.model.User;
import com.exam.model.UserHistory;
import com.exam.repo.UserHistoryRepository;
import com.exam.service.UserHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

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

    @Override
    public List<UserHistory> getUserHistory(User user, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber,4);
        return this.userHistoryRepository.findByUser(user,pageable);
    }

    @Override
    public List<UserHistory> getUserSpecificHistory(String category, User user, int pageNumber) {
        System.out.println(category);
        System.out.println(user);
        Pageable pageable = PageRequest.of(pageNumber,4);
        return this.userHistoryRepository.findByCategoryAndUser(category,user,pageable);
    }


    @Override
    public List<UserHistory> getQuizAttempts(Long qid, int pageNumber, String searchText1, String searchText2) {
        Pageable pageable = PageRequest.of(pageNumber,4);
        if (searchText1.equals("") && searchText2.equals("")) {
            return this.userHistoryRepository.findByQid(qid,pageable);
        }else{
            return userHistoryRepository.findByUser_UsernameContainingIgnoreCaseAndDateContainingIgnoreCase(
                    searchText1, searchText2, pageable);
        }
    }


}
