package com.vaitkevicius.main.auth.services;

import com.vaitkevicius.main.auth.data.CustomUserDetails;
import com.vaitkevicius.main.user.data.db.User;
import com.vaitkevicius.main.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

//    @Autowired
//    private MessageSource message;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getUserByEmail(email);
        if (user == null) {
//            throw new UsernameNotFoundException(message.getMessage("auth.message.userNotFound", new Object[]{username},
            throw new UsernameNotFoundException("error, user not found");
        }
        return new CustomUserDetails(user);
    }
}


