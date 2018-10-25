package com.vaitkevicius.main.user.validators;

import com.vaitkevicius.main.user.data.dto.UserDto;
import com.vaitkevicius.main.common.validation.validators.AbstractValidator;
import com.vaitkevicius.main.user.data.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
@Log4j2
public class UserValidator extends AbstractValidator<UserDto> {

    @Autowired
    UserRepository userRepository;

    @Override
    public void doValidate(UserDto userDto, Errors errors){
    }

    public void validateCreate(UserDto userDto, Errors errors){
        validateLogin( userDto.getLogin(), errors);
        passwordLength(userDto.getPassword(), errors);
    }

    private void validateLogin( String login, Errors errors){
        if(login != null) {
            if(userRepository.findOneByLogin(login) != null) {
                errors.rejectValue("User with this Login already exist {}", login);
            }
        }
    }

    private void passwordLength(String password, Errors errors){
        if(password.length() > 6){
            errors.rejectValue("User password {} is too long", password);
        }
    }

    public void getUser(String id){
        if(!userRepository.existsById(id)) {
            log.error("The user with id {} does not exists", id);
            throw new IllegalArgumentException("The user with id does not exists");
        }
    }

    public void deleteUser(String id){
        if(!userRepository.existsById(id)) {
            log.error("The user with id {} does not exists", id);
            throw new IllegalArgumentException("The user with id does not exists");
        }
    }
}
