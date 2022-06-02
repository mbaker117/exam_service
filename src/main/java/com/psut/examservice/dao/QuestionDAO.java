package com.psut.examservice.dao;

import com.psut.examservice.enums.Type;
import com.psut.examservice.model.Question;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDAO  extends PagingAndSortingRepository<Question,Long>, QueryByExampleExecutor<Question> {

    public List<Question> findByType(Type type);
}
