package com.vaitkevicius.main.user.controllers;

import com.vaitkevicius.main.common.UrlConst;
import com.vaitkevicius.main.common.httpresponsemessage.ResponseMessage;
import com.vaitkevicius.main.common.validation.groups.Update;
import com.vaitkevicius.main.user.data.dto.UserDto;
import com.vaitkevicius.main.common.validation.groups.Create;
import com.vaitkevicius.main.user.converters.UserConverter;
import com.vaitkevicius.main.user.services.UserService;
import com.vaitkevicius.main.user.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = UrlConst.USERS)
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserValidator userValidator;

    @Autowired
    private MessageSource messages;

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasAuthority('ADMIN')")
    public UserDto getUser(@PathVariable String id) {
        userValidator.validateGetUser(id);
        return new UserConverter().convertToDto(userService.getUser(id));
    }

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UserDto> getAllUsers() {
        userValidator.validateGetAllUsers();
        return new UserConverter().toDto(userService.getAllUsers()
        );
    }

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseMessage deleteUser(@PathVariable String id) {
        userValidator.validateGetUser(id);
        userService.deleteUser(id);
        return ResponseMessage.getSuccess(messages, "message.success.delete.user");
    }

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseMessage createUser(@Validated({Create.class}) @RequestBody UserDto userDto, BindingResult bindingResult) {
        userValidator.validateCreateUser(userDto, bindingResult);
        userService.saveUser(new UserConverter().convertToEntity(userDto));

        return ResponseMessage.getSuccess(messages, "message.success.create.user");
    }

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @RequestMapping(method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseMessage updateUser( @PathVariable String id, @Validated({Update.class}) @RequestBody UserDto userDto, BindingResult bindingResult) {
        userValidator.validateUpdateUser(userDto, bindingResult, id);
        userService.updateUser(id, new UserConverter().convertToEntity(userDto));

        return ResponseMessage.getSuccess(messages, "message.success.update.user");
    }
}
