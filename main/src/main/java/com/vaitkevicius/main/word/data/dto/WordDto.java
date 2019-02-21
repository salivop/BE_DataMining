package com.vaitkevicius.main.word.data.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WordDto {

    @NotNull
    private String id;

    @NotBlank
    @Indexed(unique = true)
    private String mainWordFormLT;
    private String mainWordFormWithoutLTLetter;

    private String mainWordFormLTRoot;
    private String mainWordFormRootWithoutLTLetter;

    private Date wordSaveTime;

    private String wordType;
    private String category;
    private String wordWeight;
    private String semanticEvaluation;
}

