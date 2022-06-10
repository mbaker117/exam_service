package com.psut.examservice.beans;

import com.psut.examservice.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamGenerateRequest {

    private Type type;

    private String title;

    private String description;

    private String examinerId;

    private String examineId;

    private int numberOfQuestions;

}
