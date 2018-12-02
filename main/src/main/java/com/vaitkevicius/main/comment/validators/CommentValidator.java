package com.vaitkevicius.main.comment.validators;

import com.vaitkevicius.main.comment.data.db.Comment;
import com.vaitkevicius.main.comment.data.dto.CommentDto;
import com.vaitkevicius.main.comment.data.repositories.CommentRepository;
import com.vaitkevicius.main.common.validation.validators.AbstractValidator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
@Log4j2
public class CommentValidator extends AbstractValidator {

    @Autowired
    CommentRepository commentRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
    }

    public void validateGetComment(String id) {
        if (!commentRepository.existsById(id)) {
            throwException(null, GET, "CommentDto.id.doesNotExist");
        }
    }

    public void validateGetAllComments() {
        if (commentRepository.findAll().size() == 0) {
            throwException(null, GET, "CommentDto.all.doesNotExist");
        }
    }

    public void validateCreate(CommentDto commentDto, Errors errors) {
        throwIfHaveErrors(errors, SAVE);
        validateCommon(commentDto, errors, null);
        throwIfHaveErrors(errors, SAVE);
    }

    private void validateCommon(CommentDto commentDto, Errors errors, Comment dbComment) {
        validateCommentValue(commentDto, null, errors);
    }

    private void validateCommentValue(CommentDto commentDto, Comment dbComment, Errors errors) {
        if (!true) {
            System.out.println("TEST");
        }
    }

    public void validateUpdateComment(CommentDto commentDto, Errors errors, String id){
        throwIfHaveErrors(errors, UPDATE);
        Comment comment = commentRepository.findOneById(id);

        if (comment == null) {
            throwResourceNotFound("CommentDto.comment.notFound", comment.getId());
        }
        throwIfHaveErrors(errors, UPDATE);
    }



    public void validateDeleteComment(String id){
        if (!commentRepository.existsById(id)) {
            throwException(null, GET, "CommentDto.id.doesNotExist");
        }
    }


}
