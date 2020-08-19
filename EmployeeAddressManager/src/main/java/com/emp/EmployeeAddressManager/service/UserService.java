package com.emp.EmployeeAddressManager.service;

import com.emp.EmployeeAddressManager.document.UserDocument;

public interface UserService {

    UserDocument signUp(UserDocument userDocument);

    UserDocument login(String email, String password);
}
