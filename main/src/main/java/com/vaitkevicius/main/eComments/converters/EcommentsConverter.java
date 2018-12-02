package com.vaitkevicius.main.eComments.converters;

import com.vaitkevicius.main.common.converter.AbstractConverter;
import com.vaitkevicius.main.eComments.data.db.Ecomments;
import com.vaitkevicius.main.eComments.data.dto.EcommentsDto;

/**
 * *
 * Created By Povilas 02/12/2018
 * *
 **/
public class EcommentsConverter extends AbstractConverter<Ecomments, EcommentsDto> {

    @Override
    public EcommentsDto convertToDto(Ecomments ecomments){
        return EcommentsDto.builder()
                .id(ecomments.getId())
                .eComment((ecomments.getEComment()))
                .build();
    }

    @Override
    public Ecomments convertToEntity(EcommentsDto ecommentsDto){
        return Ecomments.builder()
                .id(ecommentsDto.getId())
                .eComment(ecommentsDto.getEComment())
                .build();
    }
}
