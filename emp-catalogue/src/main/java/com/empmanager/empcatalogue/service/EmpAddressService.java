package com.empmanager.empcatalogue.service;

import com.empmanager.empcatalogue.model.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmpAddressService {
    List<Address> getAddressList(String username,String password);
}
