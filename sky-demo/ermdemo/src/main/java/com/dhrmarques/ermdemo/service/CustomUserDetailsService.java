package com.dhrmarques.ermdemo.service;

import com.dhrmarques.ermdemo.model.SecurityTBUser;
import com.dhrmarques.ermdemo.repository.TBUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final TBUserRepository repository;

    @Autowired
    public CustomUserDetailsService(TBUserRepository repository) {this.repository = repository;}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByEmail(username)
            .map(SecurityTBUser::new)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
