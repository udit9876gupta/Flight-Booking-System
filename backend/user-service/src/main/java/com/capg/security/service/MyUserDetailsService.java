package com.capg.security.service;

import com.capg.entity.UserData;
import com.capg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> roles = null;
        Optional<UserData> userData = userRepository.getUserDataByUsername(userName);

        if (userData.isEmpty()) {
            throw new UsernameNotFoundException(userName);
        }
        else {
            if (userName.equals("udit")) {
                roles = List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
                return new User("udit", "password", roles);
            }
            else {
                String username = userData.get().getUsername();
                String password = userData.get().getUserPassword();
                roles = List.of(new SimpleGrantedAuthority("ROLE_USER"));
                return new User(username, password, roles);
            }
        }
    }
}
