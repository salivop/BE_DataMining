package com.vaitkevicius.main.comment.data.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    @NotNull
    private String id;

    private String commentAuthor;
    private String date;
    private String comment;
    private String cleanedComment;

    private List<String> words;
    private List<String> mainWordFormsLT;
    private List<String> mainWordFormsLTRoots;

    private double positiveCommentValue;
    private double negativeCommentValue;
}
