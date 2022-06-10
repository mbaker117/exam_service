package com.psut.examservice.service.impl;

import com.google.common.base.Preconditions;
import com.psut.examservice.beans.ExamGenerateRequest;
import com.psut.examservice.dao.ExamDAO;
import com.psut.examservice.dao.QuestionDAO;
import com.psut.examservice.enums.Type;
import com.psut.examservice.model.Exam;
import com.psut.examservice.model.Question;
import com.psut.examservice.service.ExamService;
import com.psut.examservice.service.SequenceService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Qualifier("examService")
public class DefaultExamService implements ExamService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultQuestionService.class);
    public static final String EXAMINER_ID_IS_EMPTY = "examinerId is Empty";
    public static final String EXAMINE_ID_IS_EMPTY = "examineId is Empty";
    public static final String TYPE_IS_NULL = "type is null";
    public static final String REQUEST_IS_NULL = "request is NULL";

    @Autowired
    private QuestionDAO questionDAO;

    @Autowired
    private ExamDAO examDAO;

    @Autowired
    private SequenceService sequenceService;


    @Override
    public Optional<Exam> generateExam(ExamGenerateRequest request) {
        Preconditions.checkArgument(Objects.nonNull(request), REQUEST_IS_NULL);
        Preconditions.checkArgument(Strings.isNotBlank(request.getExaminerId()), EXAMINER_ID_IS_EMPTY);
        Preconditions.checkArgument(Strings.isNotBlank(request.getExamineId()), EXAMINE_ID_IS_EMPTY);
        Preconditions.checkArgument(Objects.nonNull(request.getType()), TYPE_IS_NULL);


        List<Question> questions = questionDAO.findByType(request.getType());

        long questionNumbers = questions.size();
        if (questionNumbers <= 0) {
            LOGGER.warn("Can't create exam due no question found");
            return Optional.empty();
        }

        Set<Question> examQuestion = getExamQuestion(questions, request.getNumberOfQuestions());
        Exam exam = getExam(request, examQuestion);


        return Optional.of(exam);
    }

    @Override
    public Optional<Exam> getExamById(long id) {
        return examDAO.findById(id);
    }

    @Override
    public Optional<Exam> generateExam(ExamGenerateRequest request, List<Question> questions) {
        Preconditions.checkArgument(Objects.nonNull(request), REQUEST_IS_NULL);
        Preconditions.checkArgument(Strings.isNotBlank(request.getExaminerId()), EXAMINER_ID_IS_EMPTY);
        Preconditions.checkArgument(Strings.isNotBlank(request.getExamineId()), EXAMINE_ID_IS_EMPTY);
        Preconditions.checkArgument(Objects.nonNull(request.getType()), TYPE_IS_NULL);


        Preconditions.checkArgument(CollectionUtils.isNotEmpty(questions),"questions is empty");
        Exam exam = getExam(request, new HashSet<>(questions));
        return Optional.of(exam);
    }

    private Exam getExam(ExamGenerateRequest request, Set<Question> examQuestion) {
        Exam exam = new Exam();
        exam.setExamineId(request.getExamineId());
        exam.setExaminerId(request.getExaminerId());
        exam.setQuestions(examQuestion);
        exam.setType(request.getType());
        exam.setTitle(request.getTitle());
        exam.setDescription(request.getDescription());
        exam.setId(sequenceService.generateSequence(Exam.SEQUENCE_NAME));
        exam.setCreationDate(LocalDateTime.now());
        exam = examDAO.save(exam);
        LOGGER.info("Exam created with ID {}", exam.getId());
        return exam;
    }

    private Set<Question> getExamQuestion(List<Question> questions, int maxNumber) {
        Set<Question> examQuestions = new LinkedHashSet<>();

        int questionNumbers = Math.min(maxNumber, questions.size());
        while (examQuestions.size() < questionNumbers) {
            Question question = questions.get(new Random().nextInt(questionNumbers));
            examQuestions.add(question);
        }
        return examQuestions;
    }
}
