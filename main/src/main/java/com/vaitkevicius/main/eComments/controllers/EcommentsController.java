package com.vaitkevicius.main.eComments.controllers;

import com.vaitkevicius.main.common.UrlConst;
import com.vaitkevicius.main.common.httpresponsemessage.ResponseMessage;
import com.vaitkevicius.main.eComments.data.dto.EcommentsDto;
import com.vaitkevicius.main.eComments.services.EcommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 * *
 * Created By Povilas 02/12/2018
 * *
 **/
@RestController
@RequestMapping(value = UrlConst.E_COMMENT)
public class EcommentsController {

    @Autowired
    EcommentsService ecommentsService;

    @Autowired
    private MessageSource messages;

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseMessage saveEcomments(@PathParam("{url : (.+)?}") String url) throws Exception {
        ecommentsService.saveEcomments(url);
        return ResponseMessage.getSuccess(messages, "message.success.create.ecomments");
    }
}
