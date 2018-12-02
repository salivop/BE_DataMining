package com.vaitkevicius.main.eComments.data.dto;

import lombok.Builder;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * *
 * Created By Povilas 02/12/2018
 * *
 **/
@Data
@Builder
public class EcommentsDto {

    @NotNull
    private String id;

    private String eComment;
}
