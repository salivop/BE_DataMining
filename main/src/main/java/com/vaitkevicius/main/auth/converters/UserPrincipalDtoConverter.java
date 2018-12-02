//package com.vaitkevicius.main.auth.converters;
//
//import com.vaitkevicius.main.auth.data.dto.UserPrincipalDto;
//import com.vaitkevicius.main.user.data.db.User;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * *
// * Created By Povilas 02/12/2018
// * *
// **/
//public class UserPrincipalDtoConverter {
//    public UserPrincipalDto convert(UserDetails userDetails) {
//        User user = userDetails.getUser();
//        return UserPrincipalDto.builder()
////                .id(user.getId())
//                .email(user.getEmail())
//                .name(user.getName())
//                .surname(user.getSurname())
////                .language(user.getLanguage())
////                .thumbnail(user.getThumbnail())
////                .permissions(getPermissions(userDetails.getAuthorities()))
//                .build();
//    }
//
//    public UserPrincipalDto convert(Object principal, User user) {
//        UserDetails userDetails = (UserDetails) principal;
//        return UserPrincipalDto.builder()
//                .id(user.getId())
//                .username(userDetails.getUsername())
//                .email(user.getEmail())
//                .name(user.getName())
//                .surname(user.getSurname())
//                .language(user.getLanguage())
//                .thumbnail(user.getThumbnail())
//                .permissions(getPermissions(userDetails.getAuthorities()))
//                .build();
//    }
//
////    private Set<UserPermissionDto> getPermissions(Collection<? extends GrantedAuthority> grantedAuthorities) {
////        Set<UserPermissionDto> userPermissions = new HashSet<>();
////        for (GrantedAuthority a : grantedAuthorities) {
////            userPermissions.add(UserPermissionDto.builder().code(a.getAuthority()).build());
////        }
////        return userPermissions;
////    }
//}
