package com.exam.repo;

import com.exam.model.UserCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface UserCategoryRepository extends JpaRepository<UserCategory, Long> {

    List<UserCategory> findAllByUser_Id(Long userId);

    List<UserCategory> findByUserIdAndIsPaidTrue(Long userId);

    List<UserCategory> findByUserIdOrIsPaidOrUser_Email(Long userId,boolean b,String email);

    Optional<UserCategory> findByCidAndUser_Id(Long cid,Long userId);

    void deleteById(Long userCategoryId);

    void deleteByCid(Long cid);

    List<UserCategory> findAllByCid(Long categoryId);
}
