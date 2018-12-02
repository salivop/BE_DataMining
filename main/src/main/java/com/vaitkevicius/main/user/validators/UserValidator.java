package com.vaitkevicius.main.user.validators;

import com.vaitkevicius.main.user.data.db.User;
import com.vaitkevicius.main.user.data.dto.UserDto;
import com.vaitkevicius.main.common.validation.validators.AbstractValidator;
import com.vaitkevicius.main.user.data.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
@Log4j2
public class UserValidator extends AbstractValidator {

    private final int PASSWORD_LENGTH = 7;

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
    }

    public void validateGetUser(String id) {
        if (!userRepository.existsById(id)) {
            throwException(null, GET, "UserDto.id.doesNotExist");
        }
    }

    public void validateGetAllUsers() {
        if (userRepository.findAll().size() == 0) {
            throwException(null, GET, "UserDto.all.doesNotExist");
        }
    }


    public void validateCreateUser(UserDto userDto, Errors errors) {
        throwIfHaveErrors(errors, SAVE);
        validateCommon(userDto, errors, null);
        throwIfHaveErrors(errors, SAVE);

    }

    private void validateCommon(UserDto userDto, Errors errors, User dbUser) {
        validateEmail(userDto, dbUser, errors);
        validatePassword(userDto, errors);
    }

    private void validateEmail(UserDto userDto, User dbUser, Errors errors) {
        User overlappingNameUser = userRepository.findOneByEmail(userDto.getEmail());
        if ((dbUser == null && overlappingNameUser != null)
                || (dbUser != null && overlappingNameUser != null && !overlappingNameUser.getId().equals(userDto.getId()))) {
            log.error("A user with email '{}' already exists.", userDto.getEmail());
            rejectValue(errors, "email", "UserDto.email.isNotUnique", userDto.getEmail());
        }
    }

    private void validatePassword(UserDto userDto, Errors errors) {
        if (userDto.getPassword().length() == PASSWORD_LENGTH) {
            rejectValue(errors, "password", "UserDto.password.incorrect");
            return;
        }
    }

    public void validateUpdateUser(UserDto userDto, Errors errors, String id) {
        throwIfHaveErrors(errors, UPDATE);
        User user = userRepository.findOneById(id);

        if(user == null ) {
            throwResourceNotFound("UserDto.user.notFound", userDto.getId());
        }
        throwIfHaveErrors(errors, UPDATE);
    }
}
