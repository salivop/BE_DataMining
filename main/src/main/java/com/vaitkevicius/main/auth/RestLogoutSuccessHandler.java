//package com.vaitkevicius.main.auth;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.vaitkevicius.main.common.UrlConst;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * *
// * Created By Povilas 12/11/2018
// * *
// **/
//@Component
//public class RestLogoutSuccessHandler implements LogoutSuccessHandler {
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    @Override
//    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//
//        response.setStatus(HttpStatus.OK.value());
//        objectMapper.writeValue(response.getWriter(), "Bye!");
//
////        String URL = request.getContextPath() + UrlConst.HOME;
////        response.setStatus(HttpStatus.OK.value());
////        response.sendRedirect(URL);
//
//
//    }
//}
