package com.vaitkevicius.main.role.validators;


import com.vaitkevicius.main.role.data.db.Roles;
import com.vaitkevicius.main.role.data.dto.RolesDto;
import com.vaitkevicius.main.role.data.repositories.RolesRepository;
import com.vaitkevicius.main.common.validation.validators.AbstractValidator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
@Log4j2
public class RoleValidator extends AbstractValidator {

    @Autowired
    RolesRepository rolesRepository;


    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
    }

    public void validateGetRole(String id){
        if(!rolesRepository.existsById(id)){
            throwException(null, GET, "RoleDto.id.doesNotExist");
        }
    }

    public void validateGetAllRoles(){
        if(rolesRepository.findAll().size() == 0) {
            throwException(null, GET, "RoleDto.all.doesNotExist");
        }
    }

    public void validateCreateRole(RolesDto rolesDto, Errors errors){
        throwException(errors, SAVE);
        validateCommon(rolesDto, errors, null);
        throwException(errors, SAVE);
    }

    public void validateCommon(RolesDto rolesDto, Errors errors, Roles dbRole){
        validateRoleName(rolesDto, dbRole, errors);
    }

    private void validateRoleName(RolesDto rolesDto, Roles dbRole, Errors errors){
        Roles overlappingRoleName = rolesRepository.findOneById(rolesDto.getId());
        if((dbRole == null && overlappingRoleName != null)
        || (dbRole != null && overlappingRoleName != null && !overlappingRoleName.getId().equals(rolesDto.getId()))) {
            rejectValue(errors, "role", "RoleDto.role.isNotUnique");
        }
    }
}
