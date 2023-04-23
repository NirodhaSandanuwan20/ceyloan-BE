package com.exam.repo;

import com.exam.model.UserCategory;
import com.exam.model.UserHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserCategoryRepository extends JpaRepository<UserCategory, Long> {
    List<UserCategory> findAllByUser_Id(Long id);
    Optional<UserCategory> findByCidAndUser_Id(Long cid,Long id);
}
