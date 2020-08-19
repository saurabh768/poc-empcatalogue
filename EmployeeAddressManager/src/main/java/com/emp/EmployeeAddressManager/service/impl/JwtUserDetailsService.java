package com.emp.EmployeeAddressManager.service.impl;

import com.emp.EmployeeAddressManager.document.UserDocument;
import com.emp.EmployeeAddressManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDocument userDocument=null;

        if(userRepository.findByEmail(username)==null){
            userDocument=userRepository.findById(username);
        }
        else
        {
            userDocument=userRepository.findByEmail(username);
        }
        if (userDocument == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(userDocument.getUserId(), userDocument.getPassword(),
                new ArrayList<>());
    }
}

