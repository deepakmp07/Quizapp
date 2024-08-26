package com.deepak.quiz_service.service;

import org.apache.tomcat.util.digester.ArrayStack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.deepak.quiz_service.dao.QuizDao;
import com.deepak.quiz_service.feign.QuizInterface;
import com.deepak.quiz_service.model.Question;
import com.deepak.quiz_service.model.QuestionWrapper;
import com.deepak.quiz_service.model.Quiz;
import com.deepak.quiz_service.model.Response;

import java.util.*;

@Service
public class QuizService {

    @Autowired
    private QuizDao quizdao;

    @Autowired
    private QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        System.out.println(category + "----" + numQ + "-----" + title);
        ArrayList<Integer> questions = quizInterface.generate(category, numQ).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizdao.save(quiz);

        // List<Question> q = quesdao.findByCategory(category);

        // Collections.shuffle(q);
        // ArrayList<Question> al = new ArrayList();

        // for (int i = 0; i < Math.min(q.size(), numQ); i++) {
        // al.add(q.get(i));
        // }

        // quiz.setQuestions(al);
        // quizdao.save(quiz);
        // System.out.println(q);

        return new ResponseEntity<>("sucessful", HttpStatus.OK);
    }

    public List<Quiz> getAllQuiz() {
        return quizdao.findAll();
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Quiz quiz = quizdao.findById(id).get();
        // List<Question> questionsFromDB = quiz.get().getQuestions();
        List<Integer> questionIds = quiz.getQuestionIds();
        System.out.println(questionIds + "--------");
        System.out.println(quizInterface.getQuestions(questionIds).getBody());
        // List<QuestionWrapper> quesId1 = new ArrayStack<>();
        // try{
        List<QuestionWrapper> quesId = quizInterface.getQuestions(questionIds).getBody();
        System.out.println(quesId + "!!!!");
        // }
        // catch(Exception e){
        // System.out.println(e+"-!!!!");2
        // }
        // List<QuestionWrar> qw = new ArrayList<>();
        // for (Question q : questionsFromDB) {
        // QuestionWrapper temp = new QuestionWrapper(q.getId(), q.getQuestionTitle(),
        // q.getOption1(), q.getOption2(),
        // q.getOption3(), q.getOption4());
        // qw.add(temp);
        // }

        return new ResponseEntity<>(quesId, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(int id, List<Response> response) {

       ResponseEntity<Integer> score = quizInterface.calculateScore(response);

        return score;
    }
}
