package com.vaitkevicius.main.comment.controllers;

import com.vaitkevicius.main.comment.converters.CommentConverter;
import com.vaitkevicius.main.comment.data.dto.CommentDto;
import com.vaitkevicius.main.comment.services.CommentService;
import com.vaitkevicius.main.comment.validators.CommentValidator;
import com.vaitkevicius.main.common.validation.groups.Create;
import com.vaitkevicius.main.common.validation.groups.Update;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@Log4j2
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    CommentValidator commentValidator;

    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public CommentDto getComment(@PathVariable String id) {
        commentValidator.getComment(id);
        log.info("Getting comment by id: {}", id);
        return new CommentConverter().toDto(commentService.findCommentById(id));
    }

    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<CommentDto> getAllComments() {
        log.info("Getting all comments");
        return new CommentConverter().toDto(commentService.getAllComments()
        );
    }

    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> saveComment(@RequestBody @Validated(Create.class) CommentDto commentDto, Errors errors){
        commentValidator.validateCreate(commentDto, errors);
        log.info("Saving comment to DB");
        if(errors.hasErrors()) {
            return new ResponseEntity<>(errors.getAllErrors(), HttpStatus.BAD_REQUEST);
        }
        CommentConverter commentConverter = new CommentConverter();

        return new ResponseEntity<>(commentConverter.toDto(commentService.saveComment(commentConverter.toEntity(commentDto))),
                HttpStatus.OK);
    }

    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateComment(@RequestBody @Validated(Update.class) CommentDto commentDto, Errors errors) {
        if(errors.hasErrors()) {
            return new ResponseEntity<>(errors.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        CommentConverter commentConverter = new CommentConverter();
        return new ResponseEntity<>(commentConverter.toDto(commentService.saveComment(commentConverter.toEntity(commentDto))),
                HttpStatus.OK);
    }

    @ResponseBody
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteComment(@PathVariable String id) {
        commentValidator.deleteComment(id);
        log.info("Deleting comment by id: {}", id);
        commentService.removeComment(commentService.findCommentById(id));
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
