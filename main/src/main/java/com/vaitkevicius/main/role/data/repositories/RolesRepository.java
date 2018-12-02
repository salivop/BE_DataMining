package com.vaitkevicius.main.role.data.repositories;

import com.vaitkevicius.main.role.data.db.Roles;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolesRepository extends MongoRepository<Roles, String> {

    List<Roles> findOneByRoles(String roles);

//    Roles findOneByRole(String role);

    Roles findOneById(String id);
}
