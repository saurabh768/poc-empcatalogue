package com.empmanager.empcatalogue.resource;

import com.empmanager.empcatalogue.model.Address;
import com.empmanager.empcatalogue.model.LoginDetails;
import com.empmanager.empcatalogue.service.EmpAddressService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/empcatalogue/address")
@RestController
public class EmpAddressResource {

    @Autowired
    EmpAddressService empAddressService;

    private static final Logger logger = LoggerFactory.getLogger(EmpAddressResource.class);

    @GetMapping("/getaddresslist")
    ResponseEntity<?> getAddressList(@RequestBody LoginDetails loginDetails)
    {
        logger.info("START : Inside getAddressList method");
        List<Address> addressList=empAddressService.getAddressList(loginDetails.getUsername(),loginDetails.getPassword());
        logger.info("END : Inside getAddressList method :: ");
        return ResponseEntity.ok(addressList);
    }
}
