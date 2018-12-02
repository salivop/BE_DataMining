package com.vaitkevicius.main.user.services;

import com.vaitkevicius.main.common.utils.ExceptionFactory;
import com.vaitkevicius.main.role.data.repositories.RolesRepository;
import com.vaitkevicius.main.user.data.db.User;
import com.vaitkevicius.main.user.data.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private ExceptionFactory exceptionFactory;

    public User getUser(String id) {
        User user = userRepository.findOneById(id);
        if (user == null) {
            throw exceptionFactory.getResourseNotFoundException("message.error.userNotFound", id);
        }
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(String id) { userRepository.deleteById(id);}

    public User saveUser(User user) {
        user.setRoles(rolesRepository.findOneByRoles("ADMIN"));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        return userRepository.save(user);
    }

    public User updateUser(String id, User user) {
        log.info("Updating user {}", user);
        User dbUser = getUser(id);
        dbUser.setName(user.getName());
        dbUser.setSurname(user.getSurname());
        dbUser.setEmail(user.getEmail());
        dbUser.setPhoneNo(user.getPhoneNo());
        dbUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(dbUser);
    }

    public User getUserByEmail(String email) { return userRepository.findOneByEmail(email);}


}
