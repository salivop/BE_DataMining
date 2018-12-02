package com.vaitkevicius.main.role.converters;

import com.vaitkevicius.main.role.data.db.Roles;
import com.vaitkevicius.main.role.data.dto.RolesDto;
import com.vaitkevicius.main.common.converter.AbstractConverter;

public class RoleConverter extends AbstractConverter<Roles, RolesDto> {

    @Override
    public RolesDto convertToDto(Roles roles) {
        return RolesDto.builder()
                .id(roles.getId())
                .roles(roles.getRoles())
                .build();
    }

    @Override
    public Roles convertToEntity(RolesDto rolesDto) {
        return Roles.builder()
                .id(rolesDto.getId())
                .roles(rolesDto.getRoles())
                .build();
    }
}
