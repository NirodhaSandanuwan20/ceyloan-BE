package com.exam.controller;

import com.exam.model.exam.Category;
import com.exam.model.exam.Quiz;
import com.exam.service.QuizService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {
    @Autowired
    private QuizService quizService;

    //add quiz service
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    public ResponseEntity<Quiz> add(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(this.quizService.addQuiz(quiz));
    }

    //update quiz
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/")
    public ResponseEntity<Quiz> update(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
    }

    //get quiz
    @GetMapping("/")
    public ResponseEntity<?> quizzes(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "") String searchText1, @RequestParam(defaultValue = "") String searchText2) {
        return ResponseEntity.ok(this.quizService.getQuizzes(pageNumber, searchText1, searchText2));
    }

    //get single quiz
    @GetMapping("/{qid}")
    public Quiz quiz(@PathVariable("qid") Long qid) {
        return this.quizService.getQuiz(qid);
    }

    //delete the quiz
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{qid}")
    public void delete(@PathVariable("qid") Long qid) {
        this.quizService.deleteQuiz(qid);
    }

    @GetMapping("/category/{cid}")
    public List<Quiz> getQuizzesOfCategory(@PathVariable("cid") Long cid) {
        Category category = new Category();
        category.setCid(cid);
        return this.quizService.getQuizzesOfCategory(category);
    }

    //get active quizzes
    @GetMapping("/active")
    public List<Quiz> getActiveQuizzes(@RequestParam(defaultValue = "0") int pageNumber, @RequestParam(defaultValue = "") String searchText1, @RequestParam(defaultValue = "") String searchText2) {
        return this.quizService.getActiveQuizzes(pageNumber, searchText1, searchText2);
    }

    //get active quizzes of category user
    @GetMapping("/category/active")
    public List<Quiz> getActiveQuizzes(@RequestParam String categoryName, @RequestParam(defaultValue = "0") int pageNumber) {
        System.out.println(categoryName);
        Category category = new Category();
        category.setTitle(categoryName);
        System.out.println(category);
        return this.quizService.getActiveQuizzesOfCategory(category,pageNumber);
    }




}
