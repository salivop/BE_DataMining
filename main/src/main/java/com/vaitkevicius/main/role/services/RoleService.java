package com.vaitkevicius.main.role.services;


import com.vaitkevicius.main.common.utils.ExceptionFactory;
import com.vaitkevicius.main.role.data.db.Roles;
import com.vaitkevicius.main.role.data.repositories.RolesRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class RoleService {

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    private ExceptionFactory exceptionFactory;

    public Roles getRole(String id) {

        Roles roles = rolesRepository.findOneById(id);
        if(roles == null){
            throw exceptionFactory.getResourseNotFoundException("message.error.roleNotFound", id);
        }
        return roles;
    }

    public List<Roles> getAllRoles() {
        return rolesRepository.findAll();
    }

    public void deleteRole(String id) {
        rolesRepository.deleteById(id);
    }

    public Roles saveRole(Roles roles) {
        return rolesRepository.save(roles);
    }

}
