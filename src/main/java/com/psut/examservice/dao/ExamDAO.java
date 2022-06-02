package com.psut.examservice.dao;

import com.psut.examservice.model.Exam;
import com.psut.examservice.model.Question;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamDAO extends PagingAndSortingRepository<Exam, Long>, QueryByExampleExecutor<Exam> {

    public List<Exam> findByExaminerId(String examinerId);

    public List<Exam> findByExamineId(String examineId);
}
