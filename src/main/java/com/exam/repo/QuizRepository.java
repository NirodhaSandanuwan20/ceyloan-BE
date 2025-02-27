package com.exam.repo;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    public List<Quiz> findByCategory(Category category);

    public List<Quiz> findByActive(Boolean b, Pageable pageable);

    public List<Quiz> findByCategoryTitleAndActive(String c, Boolean b, Pageable pageable);

    public List<Quiz> findByTitleContainingIgnoreCaseAndCategory_TitleContainingIgnoreCaseAndActive(
            String key1, String key2,Boolean b, Pageable pageable
    );

    public List<Quiz> findByTitleContainingIgnoreCaseAndCategory_TitleContainingIgnoreCase(
            String key1, String key2,Pageable pageable
    );
}
