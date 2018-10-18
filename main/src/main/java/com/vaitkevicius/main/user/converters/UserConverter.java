package com.vaitkevicius.main.user.converters;

import com.vaitkevicius.main.user.data.db.User;
import com.vaitkevicius.main.user.data.dto.UserDto;
import com.vaitkevicius.main.common.converter.AbstractConverter;

public class UserConverter extends AbstractConverter<User, UserDto> {

    @Override
    public UserDto convertToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .login(user.getLogin())
                .password(user.getPassword())
                .name(user.getName())
                .surname(user.getSurname())
                .email(user.getEmail())
                .phoneNo(user.getPhoneNo())
                .build();
    }

    @Override
    public User convertToEntity(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .login(userDto.getLogin())
                .password(userDto.getPassword())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .email(userDto.getEmail())
                .phoneNo(userDto.getPhoneNo())
                .build();
    }
}
