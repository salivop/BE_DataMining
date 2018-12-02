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
    @NotNull(message = "Comment does not exists")
    @NotBlank
    private String comment;
    private List<String> words;

    private HashMap<String, Integer> commentWithValue;
}
