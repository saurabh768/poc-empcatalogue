package com.emp.EmployeeAddressManager.resource;

import com.emp.EmployeeAddressManager.config.JwtRequestFilter;
import com.emp.EmployeeAddressManager.config.JwtTokenUtil;
import com.emp.EmployeeAddressManager.document.UserDocument;
import com.emp.EmployeeAddressManager.exception.ExistingUserException;
import com.emp.EmployeeAddressManager.model.JwtRequest;
import com.emp.EmployeeAddressManager.model.JwtResponseWithUsername;
import com.emp.EmployeeAddressManager.service.UserService;
import com.emp.EmployeeAddressManager.service.impl.JwtUserDetailsService;
import com.emp.EmployeeAddressManager.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/empmanager/user")
@CrossOrigin
public class UserResource {

    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    JwtRequestFilter jwtRequestFilter;

    private static final Logger logger = LoggerFactory.getLogger(UserResource.class);

    /**
     * This method is used to signup.
     *
     * @return user_id and jwt_token
     */
    @PostMapping(value = "/signup")
    public ResponseEntity<?> signUp(@RequestBody UserDocument userDocument) throws Exception {

        logger.info("Inside signup method :: start");
        if(userService.signUp(userDocument)!=null) {
            final UserDetails userDetails = userDetailsService.loadUserByUsername(userDocument.getEmail());
            final String token = jwtTokenUtil.generateToken(userDetails);
            logger.info("Inside signup method :: end");
            return  ResponseEntity.ok(new JwtResponseWithUsername(token,userDetails.getUsername()));
        }
        else
        {
            throw new ExistingUserException(Constants.EMAIL_EXISTS);
        }
    }

    /**
     * This method is used to login.
     *
     * @return user_id and jwt_token
     */
    @PostMapping("/login")
    public ResponseEntity<JwtResponseWithUsername> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        logger.info("Inside login method :: start");
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        if(userService.login(authenticationRequest.getUsername(),authenticationRequest.getPassword())==null){
            throw new UsernameNotFoundException(authenticationRequest.getUsername());
        }
        final String token = jwtTokenUtil.generateToken(userDetails);
        logger.info("Inside login method :: end");
        return ResponseEntity.ok(new JwtResponseWithUsername(token,userService.login(authenticationRequest.getUsername(),authenticationRequest.getPassword()).getUserId()));
    }


}