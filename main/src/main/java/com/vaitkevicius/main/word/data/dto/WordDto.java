package com.vaitkevicius.main.word.data.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class WordDto {

    @NotNull
    private String id;

    @NotBlank
    @Indexed(unique = true)
    private String word;
    private int wordValue;
}
