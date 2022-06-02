package com.psut.examservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection =  "answers")
public class Answer {

    @Transient
    public static final String SEQUENCE_NAME = "answers_sequence";

    @Id
    private long id;

    @Indexed
    private String questionId;

    private String answer;

    @Indexed
    private String examId;



}
