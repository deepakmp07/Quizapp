package com.deepak.quiz_service.feign;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.deepak.quiz_service.model.QuestionWrapper;
import com.deepak.quiz_service.model.Response;

@FeignClient("QUESTION-SERVICE")
// @Component
public interface QuizInterface {

    @GetMapping("questions/generate")
    public ResponseEntity<ArrayList<Integer>> generate(@RequestParam String category, @RequestParam int numQ);

    @PostMapping("questions/getquestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestions(@RequestBody List<Integer> ques);

    @PostMapping("questions/getscore")
    public ResponseEntity<Integer> calculateScore(@RequestBody List<Response> response);

}
