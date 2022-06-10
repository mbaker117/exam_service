package com.psut.examservice.model;

import com.psut.examservice.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection =  "exams")
public class Exam {

    @Transient
    public static final String SEQUENCE_NAME = "exams_sequence";
    @Id
    private long id;

    @DBRef
    private Set<Question> questions;

    @Indexed
    private String examinerId;

    @Indexed
    private String examineId;

    private LocalDateTime creationDate;

    private Type type;

    private String title;

    private String description;


    @PostConstruct
    public void postConstruct(){
        creationDate = LocalDateTime.now();
    }
}
