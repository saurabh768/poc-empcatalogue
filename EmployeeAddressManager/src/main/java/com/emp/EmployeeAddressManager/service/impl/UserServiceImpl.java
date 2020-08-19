package com.emp.EmployeeAddressManager.service.impl;

import com.emp.EmployeeAddressManager.document.UserDocument;
import com.emp.EmployeeAddressManager.repository.UserRepository;
import com.emp.EmployeeAddressManager.service.SequenceService;
import com.emp.EmployeeAddressManager.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    UserRepository userRepository;

    @Autowired
    SequenceService sequenceService;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    /**
     * This method is used to signup.
     * <p>
     * encrypts the entered password
     *
     * @return user_id and jwt_token
     */
    @Override
    public UserDocument signUp(UserDocument userDocument) {

        if (null == userRepository.findByEmail(userDocument.getEmail())) {
            logger.info("Inside signup impl method :: start");
            userDocument.setUserId(sequenceService.userSequence(UserDocument.seqName));
            userDocument.setPassword(bcryptEncoder.encode(userDocument.getPassword()));
            logger.info("attempting to save in db");
            userRepository.save(userDocument);
            logger.info("Inside signup method :: end");
            return userDocument;
        }

        return null;
    }

    /**
     * This method is used to login.
     * <p>
     * checks for password entered against the stored(encrypted) password
     *
     * @return user_id and jwt_token
     */
    @Override
    public UserDocument login(String email, String password) {
        logger.info("Inside login impl method :: start");
        UserDocument userDocument = userRepository.findByEmail(email);
        if (userDocument != null) {
            if (bcryptEncoder.matches(password, userDocument.getPassword()) == true) {
                logger.info("Inside login impl method :: end");
                return userDocument;
            }
        }
        return null;
    }
}
