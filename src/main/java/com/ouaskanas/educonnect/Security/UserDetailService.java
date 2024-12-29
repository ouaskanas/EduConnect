package com.ouaskanas.educonnect.Security;

import com.ouaskanas.educonnect.Dao.Entities.Role;
import com.ouaskanas.educonnect.Dao.Entities.User;
import com.ouaskanas.educonnect.Dao.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service
public class UserDetailService implements UserDetailsService {

    UserRepository userRepository;

    @Autowired
    public UserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username).orElseThrow(()-> new UsernameNotFoundException(username));
        return new org.springframework.security.core.userdetails
                .User(user.getEmail(), user.getPassword(),mapRoleAuthorities(user.getRole()));
    }

    private Collection<GrantedAuthority> mapRoleAuthorities(Role role) {
        return Collections.singletonList(new SimpleGrantedAuthority(role.name()));
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("No authenticated user found");
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof org.springframework.security.core.userdetails.User) {
            String username = ((org.springframework.security.core.userdetails.User) principal).getUsername();
            return userRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
        }
        if (principal instanceof String) {
            String username = (String) principal;
            return userRepository.findByEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));
        }
        throw new IllegalStateException("Authentication principal is not valid");
    }


}
