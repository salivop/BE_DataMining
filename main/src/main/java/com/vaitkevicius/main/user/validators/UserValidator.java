package com.vaitkevicius.main.user.validators;

import com.vaitkevicius.main.user.data.dto.UserDto;
import com.vaitkevicius.main.common.validation.validators.AbstractValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class UserValidator extends AbstractValidator<UserDto> {

    @Override
    public void doValidate(UserDto userDto, Errors errors){
    }

    public void passwordLength(UserDto userDto, Errors errors){
        if(userDto.getPassword().length() > 6){
            errors.rejectValue("User password is too long", "Rejected");
        }
    }

    //check if the user login exist in DB.
}
