package com.psut.examservice.controller;

import com.psut.examservice.dao.QuestionDAO;
import com.psut.examservice.enums.Type;
import com.psut.examservice.model.Question;
import com.psut.examservice.service.QuestionService;
import com.psut.examservice.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionDAO questionDAO;


    @Autowired
    private SequenceService sequenceService;

    @Autowired
    private QuestionService questionService;

    @GetMapping
    public ResponseEntity<List> getQuestions() {
        Iterable<Question> all = questionDAO.findAll();
        List<Question> questions = new LinkedList<>();
        all.forEach(q -> questions.add(q));
        return new ResponseEntity<List>(questions, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Question> addQuestion(@RequestBody Question question) {
        question.setId(sequenceService.generateSequence(Question.SEQUENCE_NAME));
        Optional<Question> question1 = questionService.addQuestion(question);
        if (question1.isPresent()) return new ResponseEntity<Question>(question1.get(), HttpStatus.OK);
        return new ResponseEntity<Question>(HttpStatus.BAD_REQUEST);

    }

    @PutMapping()
    public ResponseEntity<Question> updateQuestion(@RequestBody Question question) {
        Optional<Question> questionById = questionDAO.findById(question.getId());
        if (questionById.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        questionDAO.save(question);
        return new ResponseEntity<>(question, HttpStatus.OK);

    }

    @GetMapping("/types")
    public ResponseEntity<Type[]> getTypes() {
        return new ResponseEntity<Type[]>(Type.values(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(long id) {
        Optional<Question> questionById = questionDAO.findById(id);
        if(questionById.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        questionDAO.delete(questionById.get());
        return new ResponseEntity<>( HttpStatus.OK);
    }
}
