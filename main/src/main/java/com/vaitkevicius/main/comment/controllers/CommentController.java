package com.vaitkevicius.main.comment.controllers;

import com.vaitkevicius.main.comment.converters.CommentConverter;
import com.vaitkevicius.main.comment.data.dto.CommentDto;
import com.vaitkevicius.main.comment.services.CommentService;
//import com.vaitkevicius.main.comment.validators.CommentValidator;
import com.vaitkevicius.main.comment.validators.CommentValidator;
import com.vaitkevicius.main.common.UrlConst;
import com.vaitkevicius.main.common.httpresponsemessage.ResponseMessage;
import com.vaitkevicius.main.common.validation.groups.Create;
import com.vaitkevicius.main.common.validation.groups.Update;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
@RequestMapping(UrlConst.COMMENT)
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    CommentValidator commentValidator;

    @Autowired
    private MessageSource messages;

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('USER, ADMIN')")
    public CommentDto getComment(@PathVariable String id) {
        commentValidator.validateGetComment(id);
        return new CommentConverter().toDto(commentService.getComment(id));
    }

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<CommentDto> getAllComments() {
        commentValidator.validateGetAllComments();
        return new CommentConverter().toDto(commentService.getAllComments()
        );
    }

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseMessage saveComment(@Validated(Create.class) @RequestBody CommentDto commentDto, BindingResult bindingResult) {
        commentValidator.validateCreate(commentDto, bindingResult);
        commentService.saveComment(new CommentConverter().convertToEntity(commentDto));

        return ResponseMessage.getSuccess(messages, "message.success.create.comment");
    }

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseMessage updateComment(@PathVariable String id, @Validated
            (Update.class) @RequestBody CommentDto commentDto, BindingResult bindingResult) {
        commentValidator.validateUpdateComment(commentDto, bindingResult, id);
        commentService.saveComment(new CommentConverter().convertToEntity(commentDto));

        return ResponseMessage.getSuccess(messages, "message.success.update.comment");

    }

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseMessage deleteComment(@PathVariable String id) {
        commentValidator.validateDeleteComment(id);
        commentService.deleteComment(id);

        return ResponseMessage.getSuccess(messages, "message.success.delete.comment");
    }
}
