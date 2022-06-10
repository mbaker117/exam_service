package com.psut.examservice.controller;

import com.psut.examservice.beans.ExamGenerateDTO;
import com.psut.examservice.beans.ExamGenerateRequest;
import com.psut.examservice.dao.ExamDAO;
import com.psut.examservice.model.Exam;
import com.psut.examservice.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    public ResponseEntity<Exam> generateExamByType(@RequestBody ExamGenerateRequest request) {
        Optional<Exam> exam = examService.generateExam(request);
        if (exam.isPresent()) {
            return new ResponseEntity<Exam>(exam.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Exam>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/generate")
    public ResponseEntity<Exam> generateExam(@RequestBody ExamGenerateDTO examGenerateDTO) {
        Optional<Exam> exam = examService.generateExam(examGenerateDTO.getExamInfo(), examGenerateDTO.getQuestions());
        if (exam.isPresent()) {
            return new ResponseEntity<Exam>(exam.get(), HttpStatus.OK);
        }
        return new ResponseEntity<Exam>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/questions/examID/{examid}")
    public ResponseEntity<Set> getExamsByID(@PathVariable long examid) {
        Optional<Exam> exam = examDAO.findById(examid);
        Exam exam1 = exam.orElse(null);
        if (exam1 != null) {
            return new ResponseEntity<Set>(exam1.getQuestions(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
