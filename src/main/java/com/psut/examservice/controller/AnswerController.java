package com.psut.examservice.controller;

import com.psut.examservice.dao.AnswerDAO;
import com.psut.examservice.dao.ExamDAO;
import com.psut.examservice.enums.Type;
import com.psut.examservice.model.Answer;
import com.psut.examservice.model.Exam;
import com.psut.examservice.model.Question;
import com.psut.examservice.service.ExamService;
import com.psut.examservice.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/answers")
public class AnswerController {

    @Autowired
    private AnswerDAO answerDAO;

    @Autowired
    private SequenceService sequenceService;

    @GetMapping
    public ResponseEntity<List> getAnswers() {
        Iterable<Answer> all = answerDAO.findAll();
        List<Answer> answers = new LinkedList<>();
        all.forEach(q -> answers.add(q));
        return new ResponseEntity<List>(answers, HttpStatus.OK);
    }

    @GetMapping("/examId/{examId}")
    public ResponseEntity<List> getExamsByExaminerId(@PathVariable String examId) {
        List<Answer> answers = answerDAO.findByExamId(examId);
        return new ResponseEntity<List>(answers, HttpStatus.OK);
    }

    @GetMapping("/questionId/{questionId}")
    public ResponseEntity<List> getExamsByExamineId(@PathVariable String questionId) {
        List<Answer> answers = answerDAO.findByQuestionId(questionId);
        return new ResponseEntity<List>(answers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Answer> submitAnswer(@RequestBody Answer answer) {
        answer.setId(sequenceService.generateSequence(Answer.SEQUENCE_NAME));
        Answer save = answerDAO.save(answer);
        return new ResponseEntity<Answer>(save, HttpStatus.OK);
    }
}
