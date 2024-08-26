package com.deepak.quiz_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.deepak.quiz_service.model.Question;

import java.util.List;

@Service
public class QuestionService {


//     public ResponseEntity<List<Question>> getAllQuestions() {
//         try {
//             return new ResponseEntity<>(questiondao.findAll(), HttpStatus.OK);
//         } catch (Exception e) {
// e.printStackTrace();
//         }

//         return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//     }

    // public ResponseEntity<List<Question>> findByCategory(String category) {
    //     try{
    //         return new ResponseEntity<>(questiondao.findByCategory(category),HttpStatus.OK);
    //     }
    //     catch(Exception e){
    //          e.printStackTrace();
    //     }
    //     return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    // }

    // public ResponseEntity<Question> addQuestion(Question question) {
    //     try{
    //     return new ResponseEntity<>(questiondao.save(question),HttpStatus.OK);
    //     }
    //     catch(Exception e) {
    //         e.printStackTrace();
    //     }

    //     return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);

    // }
}
