package com.deepak.question_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.deepak.question_service.service.QuestionService;
import com.deepak.question_service.model.Question;
import com.deepak.question_service.model.QuestionWrapper;
import com.deepak.question_service.model.Response;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("allquestions")
    public ResponseEntity<List<Question>> getAllQuestions() {
        return questionService.getAllQuestions();
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category) {
        return questionService.findByCategory(category);
    }

    @PostMapping("add")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        return questionService.addQuestion(question);
    }

    @GetMapping("generate")
    public ResponseEntity<ArrayList<Integer>> generate(@RequestParam String category, @RequestParam int numQ) {
        return questionService.generate(category, numQ);
    }

    @PostMapping("getquestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Integer> ques) {
        System.out.println("-------");
        return questionService.getQuestionForQuiz(ques);
    }

    @PostMapping("getscore")
    public ResponseEntity<Integer> calculateScore(@RequestBody List<Response> response) {
        return questionService.calculateScore(response);
    }
    

    // generate
    // getQuestion
    // calculate score
}
