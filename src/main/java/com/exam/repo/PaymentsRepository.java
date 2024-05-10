package com.exam.repo;

import com.exam.model.UserPayments;
import com.exam.model.exam.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface PaymentsRepository extends JpaRepository<UserPayments, Long> {
}
