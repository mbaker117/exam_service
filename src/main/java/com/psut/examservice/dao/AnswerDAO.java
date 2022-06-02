package com.psut.examservice.dao;

import com.psut.examservice.model.Answer;
import com.psut.examservice.model.Exam;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerDAO extends PagingAndSortingRepository<Answer, Long>, QueryByExampleExecutor<Exam> {

    public List<Answer> findByExamId(String examId);

    public List<Answer> findByQuestionId(String questionId);
}
