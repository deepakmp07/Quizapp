package com.deepak.question_service.service;

import com.deepak.question_service.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.deepak.question_service.dao.QuestionDao;
import com.deepak.question_service.model.Question;
import com.deepak.question_service.model.QuestionWrapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao questiondao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questiondao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
e.printStackTrace();
        }

        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> findByCategory(String category) {
        try{
            return new ResponseEntity<>(questiondao.findByCategory(category),HttpStatus.OK);
        }
        catch(Exception e){
             e.printStackTrace();
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Question> addQuestion(Question question) {
        try{
        return new ResponseEntity<>(questiondao.save(question),HttpStatus.OK);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);

    }

    public ResponseEntity<ArrayList<Integer>> generate(String category, int num) {

        List<Question> q = questiondao.findByCategory(category);

        Collections.shuffle(q);
        ArrayList<Integer> al = new ArrayList();

        for (int i = 0; i < Math.min(q.size(), num); i++) {
            al.add(q.get(i).getId());
        }

       return new ResponseEntity<>(al, HttpStatus.OK);
    }


    public ResponseEntity<List<QuestionWrapper>> getQuestionForQuiz(List<Integer> quesList) {
        
        List<QuestionWrapper> quesForquiz = new ArrayList<>();

        for(Integer temp:quesList) {
            Question ques = questiondao.findById(temp).get();
            System.out.println("--------");
            QuestionWrapper qwrap = new QuestionWrapper(ques.getId(), ques.getQuestionTitle(),ques.getOption1(),ques.getOption2(), ques.getOption3(), ques.getOption4());
            quesForquiz.add(qwrap);
        }

        return new ResponseEntity<>(quesForquiz, HttpStatus.OK);

    }

    public ResponseEntity<Integer> calculateScore(List<Response> responses) {
        Integer right = 0;

        for(Response res:responses) {
            int quesid = res.getId();

            Question ques = questiondao.findById(quesid).get();
            
            if(res.getResponse().equals(ques.getRightAnswer())) {
                right++;
            }

        }
        
        return new ResponseEntity<>(right ,HttpStatus.OK);
    }
}
