package com.psut.examservice.service.impl;

import com.google.common.base.Preconditions;
import com.psut.examservice.dao.QuestionDAO;
import com.psut.examservice.model.Question;
import com.psut.examservice.service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Qualifier("questionService")
public class DefaultQuestionService  implements QuestionService {

    private static final Logger LOGGER =  LoggerFactory.getLogger(DefaultQuestionService.class);

    @Autowired
    private QuestionDAO questionDAO;

    @Override
    public Optional<Question> addQuestion(Question question) {
        Preconditions.checkArgument(Objects.nonNull(question),"Question is NULL");
        question = questionDAO.save(question);
        LOGGER.info("Question added with ID {}",question.getId());
        return Optional.ofNullable(question);
    }
}
