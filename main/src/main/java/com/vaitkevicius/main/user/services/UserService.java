package com.vaitkevicius.main.user.services;

import com.vaitkevicius.main.user.data.db.User;
import com.vaitkevicius.main.user.data.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getUser(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void delete(String id) { userRepository.deleteById(id);}

    public User save(User user) {
        return userRepository.save(user);
    }

    public User existByLogin(String login) {
        log.debug("Check if permission exist by login {}", login);
        return userRepository.findOneByLogin(login);
    }
}
