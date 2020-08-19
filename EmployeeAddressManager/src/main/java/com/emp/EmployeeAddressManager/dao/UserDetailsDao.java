package com.emp.EmployeeAddressManager.dao;


import com.emp.EmployeeAddressManager.document.UserDocument;
import com.emp.EmployeeAddressManager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsDao {

    @Autowired
    UserRepository userRepository;

    public UserDocument findUserByEmail(String email) {
        UserDocument userDocument = userRepository.findByEmail(email);
        return userDocument;
    }


    public UserDocument findUserById(String user_id) {
        UserDocument userDocument = userRepository.findById(user_id);
        return userDocument;
    }
}
