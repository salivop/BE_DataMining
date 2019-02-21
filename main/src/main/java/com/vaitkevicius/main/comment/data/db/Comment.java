package com.vaitkevicius.main.comment.data.db;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.List;

@Document(collection = "comments")
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    private String id;

    private String commentAuthor;
    private String date;
    private String comment;
    private String cleanedComment;

    private List<String> words;

    private List<String> mainWordFormsLT;
    private List<String> mainWordFormsLTRoots;

    private String commentCategory;

    private double positiveCommentValue;
    private double negativeCommentValue;
}