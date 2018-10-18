package com.vaitkevicius.main.user.data.dto;

import com.vaitkevicius.main.common.validation.groups.Create;
import com.vaitkevicius.main.common.validation.groups.Update;
import com.vaitkevicius.main.common.validation.validators.anotations.Name;
import lombok.Builder;
import lombok.Data;
import lombok.extern.log4j.Log4j2;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Log4j2
@Data
@Builder
public class UserDto {

    @NotNull
    private String id;

    @NotNull @NotBlank
    private String login;
    @NotNull @NotBlank
    private String password;

    @NotNull @NotBlank
    @Name(groups = { Create.class, Update.class })
    private String name;
    @NotNull @NotBlank
    private String surname;
    @Email
    private String email;
    private String phoneNo;
}
