package com.vaitkevicius.main.comment.validators;

import com.vaitkevicius.main.comment.data.dto.CommentDto;
import com.vaitkevicius.main.comment.data.repositories.CommentRepository;
import com.vaitkevicius.main.common.validation.validators.AbstractValidator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
@Log4j2
public class CommentValidator extends AbstractValidator<CommentDto> {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public void doValidate(CommentDto commentDto, Errors errors){
    };

    public void validateCreate(CommentDto commentDto, Errors errors){
    }

    public void deleteComment(String id) {
        if(!commentRepository.existsById(id)) {
            log.error("The comment with id {} does not exists", id);
            throw new IllegalArgumentException("The comment with id does not exists");
        }
    }

    public void getComment(String id) {
        if(!commentRepository.existsById(id)) {
            log.error("The comment with id {} does not exists", id);
            throw new IllegalArgumentException("The comment with id does not exists");
        }
    }
}
