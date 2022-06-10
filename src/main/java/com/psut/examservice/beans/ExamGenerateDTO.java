package com.psut.examservice.beans;

import com.psut.examservice.enums.Type;
import com.psut.examservice.model.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExamGenerateDTO {

    private ExamGenerateRequest examInfo;

    private List<Question> questions;

}
