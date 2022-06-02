package com.psut.examservice.controller;

import com.psut.examservice.dao.ExamDAO;
import com.psut.examservice.enums.Type;
import com.psut.examservice.model.Exam;
import com.psut.examservice.model.Question;
import com.psut.examservice.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/exams")
public class ExamController {

    @Autowired
    private ExamDAO examDAO;

    @Autowired
    private ExamService examService;

    @GetMapping
    public ResponseEntity<List> getExams() {
        Iterable<Exam> all = examDAO.findAll();
        List<Exam> exams = new LinkedList<>();
        all.forEach(q -> exams.add(q));
        return new ResponseEntity<List>(exams, HttpStatus.OK);
    }

    @GetMapping("/examinerId/{examinerId}")
    public ResponseEntity<List> getExamsByExaminerId(@PathVariable String examinerId) {
        List<Exam> exams = examDAO.findByExaminerId(examinerId);
        return new ResponseEntity<List>(exams, HttpStatus.OK);
    }

    @GetMapping("/examineId/{examineId}")
    public ResponseEntity<List> getExamsByExamineId(@PathVariable String examineId) {
        List<Exam> exams = examDAO.findByExamineId(examineId);
        return new ResponseEntity<List>(exams, HttpStatus.OK);
    }

    @PostMapping("/generateByType")
    public ResponseEntity<Exam> generateExamByType(@RequestParam String examinerId, @RequestParam String examineId, @RequestParam Type type, @RequestParam int numberOfQuestions) {
        Optional<Exam> exam = examService.generateExam(examinerId, examineId, numberOfQuestions, type);
        if (exam.isPresent()) {
            return new ResponseEntity<Exam>(exam.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Exam>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/generate")
    public ResponseEntity<Exam> generateExam(@RequestParam String examinerId, @RequestParam String examineId, @RequestBody List<Question> questions, @RequestParam Type type) {
        Optional<Exam> exam = examService.generateExam(examinerId, examineId, type, questions);
        if (exam.isPresent()) {
            return new ResponseEntity<Exam>(exam.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Exam>(HttpStatus.NO_CONTENT);
    }
}
