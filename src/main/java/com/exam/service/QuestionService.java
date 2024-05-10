package com.exam.service;

import com.exam.helper.UserFoundException;
import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;

import java.util.List;
import java.util.Set;

public interface QuestionService {

    Question addQuestion(Question question);

    Question updateQuestion(Question question);

    Set<Question> getQuestions();

    Question getQuestion(Long questionId);

    List<Question> getQuestionsOfQuiz(Quiz quiz, int pageNumber);

    void deleteQuestion(Long quesId);

    Question get(Long questionsId);

}
