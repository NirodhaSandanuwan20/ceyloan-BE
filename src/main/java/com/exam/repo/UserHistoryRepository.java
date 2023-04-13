package com.exam.repo;

import com.exam.model.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {
}
