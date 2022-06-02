package com.psut.examservice.service;

import com.psut.examservice.enums.Type;
import com.psut.examservice.model.Exam;
import com.psut.examservice.model.Question;

import java.util.List;
import java.util.Optional;

public interface ExamService {

    public Optional<Exam> generateExam(String examinerId, String examineId, int numberOfQuestions, Type type);

    public Optional<Exam> getExamById(long id);

    public Optional<Exam> generateExam(String examinerId, String examineId,Type type, List<Question> questions);

}
