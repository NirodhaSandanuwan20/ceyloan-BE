package com.exam.service;

import com.exam.helper.UserFoundException;
import com.exam.model.exam.Question;
import com.exam.model.exam.Quiz;

import java.util.Set;

public interface QuestionService {

    public Question addQuestion(Question question);

    public Question updateQuestion(Question question);

    public Set<Question> getQuestions();

    public Question getQuestion(Long questionId);

    public Set<Question> getQuestionsOfQuiz(Quiz quiz) throws Exception;

    public void deleteQuestion(Long quesId);

    public Question get(Long questionsId);

}
