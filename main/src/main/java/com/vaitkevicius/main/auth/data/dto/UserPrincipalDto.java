package com.vaitkevicius.main.auth.data.dto;

import lombok.*;

import java.util.Set;

/**
 * *
 * Created By Povilas 02/12/2018
 * *
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserPrincipalDto {

    private String id;
    private String username;
    private String name;
    private String surname;
    private String email;
    private String language;
    private String thumbnail;
//    private Set<UserPermissionDto> permissions;
}
