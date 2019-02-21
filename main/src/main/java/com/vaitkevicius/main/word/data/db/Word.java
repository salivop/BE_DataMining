package com.vaitkevicius.main.word.data.db;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "words")
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Word {

    @Id
    private String id;

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
