package com.vaitkevicius.main.user.controllers;

import com.vaitkevicius.main.user.data.dto.UserDto;
import com.vaitkevicius.main.common.validation.groups.Create;
import com.vaitkevicius.main.user.converters.UserConverter;
import com.vaitkevicius.main.user.services.UserService;
import com.vaitkevicius.main.user.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    UserValidator userValidator;

    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public UserDto getUser(@PathVariable String id){
        logger.info("Getting user by id: {}", id);
        return new UserConverter().toDto(userService.getUser(id));
    }

    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<UserDto> getAllUsers() {
        logger.info("Getting al users");
        return new UserConverter().toDto(userService.getAllUsers()
        );
    }

    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable String id) {
        logger.info("Deleting user by id: {}", id);
        userService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> save(@RequestBody @Validated(Create.class) UserDto userDto, Errors errors) {
        logger.info("Saving user to Mongo DB");
        userValidator.passwordLength(userDto, errors);
        if(errors.hasErrors()) {
            return new ResponseEntity<>(errors.getAllErrors(),HttpStatus.BAD_REQUEST);
        }
        UserConverter userConverter = new UserConverter();

        return new ResponseEntity<>(userConverter.toDto(userService.save(userConverter.toEntity(userDto))),
                HttpStatus.OK
        );
    }

    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> update(@RequestBody UserDto userDto, Errors errors) {
        if (errors.hasErrors()) {
            return new ResponseEntity<>(errors.getAllErrors(), HttpStatus.UNPROCESSABLE_ENTITY);
        }
        UserConverter userConverter = new UserConverter();
        return new ResponseEntity<>(userConverter.toDto(userService.save(userConverter.toEntity(userDto))),
                HttpStatus.OK
        );
    }
}
