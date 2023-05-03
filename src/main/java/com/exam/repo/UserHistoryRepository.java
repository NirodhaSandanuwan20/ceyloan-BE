package com.exam.repo;

import com.exam.model.User;
import com.exam.model.UserHistory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
    List<UserHistory> findByUser(User user,Pageable pageable);
    List<UserHistory> findByQid(Long qid, Pageable pageable);
    List<UserHistory> findByCategoryAndUser(String category, User user, Pageable pageable);

    List<UserHistory> findByUser_UsernameContainingIgnoreCaseAndDateContainingIgnoreCase(
            String searchText1, String searchText2, Pageable pageable
    );
}
