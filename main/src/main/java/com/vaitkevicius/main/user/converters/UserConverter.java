package com.vaitkevicius.main.user.converters;

import com.vaitkevicius.main.user.data.db.User;
import com.vaitkevicius.main.user.data.dto.UserDto;
import com.vaitkevicius.main.common.converter.AbstractConverter;

public class UserConverter extends AbstractConverter<User, UserDto> {

    @Override
    public UserDto convertToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .name(user.getName())
                .surname(user.getSurname())
                .phoneNo(user.getPhoneNo())
                .roles(user.getRoles())
                .build();
    }

    @Override
    public User convertToEntity(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .phoneNo(userDto.getPhoneNo())
                .roles(userDto.getRoles())
                .build();
    }
}
