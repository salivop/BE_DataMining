//package com.vaitkevicius.main.ping.controllers;
//
//import com.vaitkevicius.main.auth.converters.UserPrincipalDtoConverter;
//import com.vaitkevicius.main.auth.data.UserDetails;
//import com.vaitkevicius.main.auth.data.dto.UserPrincipalDto;
//import com.vaitkevicius.main.common.UrlConst;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * *
// * Created By Povilas 02/12/2018
// * *
// **/
//@RestController
//public class PingController {
//
//    @ResponseStatus(HttpStatus.OK)
//    @RequestMapping(value = UrlConst.PING, produces = MediaType.APPLICATION_JSON_VALUE)
//    public UserPrincipalDto ping(@RequestParam(value = "principal", required = false) boolean principal,
//                                 HttpServletRequest request, Authentication authentication) {
//        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//        if (principal) {
//            return new UserPrincipalDtoConverter().convert(userDetails);
//        }
//        return null;
//    }
//}