package com.psut.examservice.service;

import com.psut.examservice.beans.ExamGenerateRequest;
import com.psut.examservice.enums.Type;
import com.psut.examservice.model.Exam;
import com.psut.examservice.model.Question;

import java.util.List;
import java.util.Optional;

public interface ExamService {

    public Optional<Exam> generateExam(ExamGenerateRequest request);

    public Optional<Exam> getExamById(long id);

    public Optional<Exam> generateExam(ExamGenerateRequest request, List<Question> questions);

}
