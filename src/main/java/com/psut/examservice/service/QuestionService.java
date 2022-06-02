package com.psut.examservice.service;

import com.psut.examservice.model.Question;

import java.util.Optional;

public interface QuestionService {

    public Optional<Question> addQuestion(Question question);
}
