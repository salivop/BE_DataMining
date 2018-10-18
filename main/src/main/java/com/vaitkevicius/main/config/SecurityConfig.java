package com.vaitkevicius.main.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//TEST
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("java")
//                .password("123").roles("USER");
//    }
//
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http.antMatcher("/**").authorizeRequests().anyRequest().hasRole("USER")
//                .and().formLogin().loginPage("/login.jsp")
//                .failureUrl("/login").loginProcessingUrl("/login")
//                .permitAll().and().logout()
//                .logoutSuccessUrl("/listEmployees.html");
//
//    }

//    @Override
//    public void configure(AuthenticationManagerBuilder builder)
//            throws Exception {
//        builder.inMemoryAuthentication()
//                .withUser("joe")
//                .password("123")
//                .roles("ADMIN");
//    }
}
