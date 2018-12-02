package com.vaitkevicius.main.role.controllers;

import com.vaitkevicius.main.common.UrlConst;
import com.vaitkevicius.main.common.httpresponsemessage.ResponseMessage;
import com.vaitkevicius.main.role.converters.RoleConverter;
import com.vaitkevicius.main.role.data.dto.RolesDto;
import com.vaitkevicius.main.role.services.RoleService;
import com.vaitkevicius.main.common.validation.groups.Create;
import com.vaitkevicius.main.role.validators.RoleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = UrlConst.ROLES)
public class RoleController {

    @Autowired
    RoleService roleService;

    @Autowired
    RoleValidator roleValidator;

    @Autowired
    private MessageSource messages;

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public RolesDto getRole(@PathVariable String id) {
        roleValidator.validateGetRole(id);
        return new RoleConverter().toDto(roleService.getRole(id));
    }

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<RolesDto> getAllRoles() {
        roleValidator.validateGetAllRoles();
        return new RoleConverter().toDto(roleService.getAllRoles()
        );
    }

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseMessage deleteRole(@PathVariable String id) {
        roleValidator.validateGetRole(id);
        roleService.deleteRole(id);
        return ResponseMessage.getSuccess(messages, "message.success.delete.role");
    }

    @ResponseStatus(code = HttpStatus.OK)
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseMessage saveRole(@Validated({Create.class}) @RequestBody RolesDto rolesDto, BindingResult bindingResult) {
        roleValidator.validateCreateRole(rolesDto, bindingResult);
        roleService.saveRole(new RoleConverter().convertToEntity(rolesDto));

        return ResponseMessage.getSuccess(messages, "message.success.create.role");
    }
}
