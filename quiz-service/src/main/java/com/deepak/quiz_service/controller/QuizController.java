package com.deepak.quiz_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deepak.quiz_service.model.QuestionWrapper;
import com.deepak.quiz_service.model.Quiz;
import com.deepak.quiz_service.model.QuizDTO;
import com.deepak.quiz_service.model.Response;
import com.deepak.quiz_service.service.QuizService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService quizserv;

    @GetMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quiz) {
        // return new ResponseEntity<>("", HttpStatus.ACCEPTED);
        return quizserv.createQuiz(quiz.getCategory(), quiz.getNumQ(), quiz.getTitle());
    }

    @GetMapping("allquiz")
    public List<Quiz> getAllQuiz() {
        return quizserv.getAllQuiz();
    }

    @PostMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getByQuiz(@PathVariable int id) {
        return quizserv.getQuizQuestions(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> calculateScore(@PathVariable int id, @RequestBody List<Response> response) {
        return quizserv.calculateResult(id, response);
    }

}
