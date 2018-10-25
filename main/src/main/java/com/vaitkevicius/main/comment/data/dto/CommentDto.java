package com.vaitkevicius.main.comment.data.dto;


import lombok.Builder;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Log4j2
@Data
@Builder
public class CommentDto {

    @NotNull
    private String id;

    private String commentAuthor;
    private String date;
    @NotNull(message = "Comment does not exists") @NotBlank
    private String comment;

}
