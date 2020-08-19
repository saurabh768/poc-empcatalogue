package com.emp.EmployeeAddressManager.service;

import com.emp.EmployeeAddressManager.model.Address;

import java.util.List;

public interface AddressService {
    public Address addAddress(Address address, String userId);
    public List<Address> getAddressList(String user_id);
    public Address updateAddress(Address addressUpdate, int addressId, String userId);
    public Boolean deleteAddress(Integer address_id, String user_id);
}
