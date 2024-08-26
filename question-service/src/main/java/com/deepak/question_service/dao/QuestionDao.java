package com.deepak.question_service.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.deepak.question_service.model.Question;

import java.util.List;

public interface QuestionDao extends JpaRepository<Question, Integer> {

    public List<Question> findByCategory(String id);

   
}