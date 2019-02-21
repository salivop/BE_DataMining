//package com.vaitkevicius.main.auth.data;
//
//import com.vaitkevicius.main.role.data.db.Roles;
//import com.vaitkevicius.main.user.data.db.User;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//import org.apache.commons.collections4.CollectionUtils;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@ToString
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//public class CustomUserDetails implements UserDetails {
//
//    private User user;
//
//    @Override
//    public Collection<GrantedAuthority> getAuthorities() {
//        List<Roles> roles = null;
//        if (user != null) {
//            roles = user.getRoles();
//        }
//
//        return CollectionUtils.emptyIfNull(roles).stream()
//                .map(r -> new SimpleGrantedAuthority(r.getRoles()))
//                .collect(Collectors.toSet());
//    }
//
//    @Override
//    public String getPassword() {
//        return user.getPassword();
//    }
//
//    @Override
//    public String getUsername() {
//        if (this.user == null) {
//            return null;
//        }
//        return this.user.getEmail();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return this.user.isAccountNonExpired();
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return this.user.isAccountNonLocked();
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return this.user.isCredentialsNonExpired();
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return this.user.isEnabled();
//    }
//
//}
