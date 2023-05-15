package com.exam.service.impl;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import com.exam.repo.QuizRepository;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class QuizServiceImpl implements QuizService {
    @Autowired
    private QuizRepository quizRepository;

    @Override
    public Quiz addQuiz(Quiz quiz) {
        System.out.println(quiz);
        return this.quizRepository.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public List<Quiz> getQuizzes(int pageNumber,String searchText1, String searchText2) {
        Pageable pageable = PageRequest.of(pageNumber, 4);
        if (searchText1.equals("") && searchText2.equals("")) {
            return (List<Quiz>) this.quizRepository.findByActive(true,pageable);
        }else{
            return (List<Quiz>) quizRepository.findByTitleContainingIgnoreCaseAndCategory_TitleContainingIgnoreCase(
                    searchText1, searchText2, pageable);
        }
    }

    @Override
    public Quiz getQuiz(Long quizId) {
        return this.quizRepository.findById(quizId).get();
    }

    @Override
    public void deleteQuiz(Long quizId) {
        this.quizRepository.deleteById(quizId);
    }

    @Override
    public List<Quiz> getQuizzesOfCategory(Category category) {
        return this.quizRepository.findByCategory(category);
    }


    //get active quizzes

    @Override
    public List<Quiz> getActiveQuizzes(int pageNumber,String searchText1, String searchText2) {
        Pageable pageable = PageRequest.of(pageNumber, 20);
        if (searchText1.equals("") && searchText2.equals("")) {
            return (List<Quiz>) this.quizRepository.findByActive(true,pageable);
        }else{
            return (List<Quiz>) quizRepository.findByTitleContainingIgnoreCaseAndCategory_TitleContainingIgnoreCaseAndActive(
                    searchText1, searchText2,true, pageable);
        }

    }

    @Override
    public List<Quiz> getActiveQuizzesOfCategory(Category c,int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, 2);
        return this.quizRepository.findByCategoryTitleAndActive(c.getTitle(), true,pageable);
    }

}
