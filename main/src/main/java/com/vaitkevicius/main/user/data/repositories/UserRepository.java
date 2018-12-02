package com.vaitkevicius.main.user.data.repositories;

import com.vaitkevicius.main.user.data.db.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findOneByEmail(String email);

    User findOneById(String id);
}
