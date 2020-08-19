package com.emp.EmployeeAddressManager.service.impl;


import com.emp.EmployeeAddressManager.dao.UserDetailsDao;
import com.emp.EmployeeAddressManager.document.AddressDocument;
import com.emp.EmployeeAddressManager.exception.AddressNotFoundException;
import com.emp.EmployeeAddressManager.repository.AddressRepository;
import com.emp.EmployeeAddressManager.document.UserDetailsDocument;
import com.emp.EmployeeAddressManager.document.UserDocument;
import com.emp.EmployeeAddressManager.model.Address;
import com.emp.EmployeeAddressManager.repository.UserRepository;
import com.emp.EmployeeAddressManager.service.AddressService;
import com.emp.EmployeeAddressManager.service.SequenceService;
import com.emp.EmployeeAddressManager.utils.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    UserDetailsDao userDetailsDao;
    @Autowired
    SequenceService sequenceService;
    List<Address> addressList;

    private static final Logger logger = LoggerFactory.getLogger(AddressServiceImpl.class);


    /**
     * This method is used to add address
     *
     * @return Data String and Http Status
     */
    @Override
    public Address addAddress(Address address, String user_id) {
        logger.info("Inside addAddress method implementation :: start");
        UserDocument userDocument = userDetailsDao.findUserById(user_id);
        UserDetailsDocument userDetailsDocument = userDocument.getUserDetails();
        if (null == userDetailsDocument.getAddressDocument())
        {
            logger.info("Checking for previously added addresses");
            addressList = new ArrayList<>();
            sequenceService.resetSequence(AddressDocument.seqName);
        }
        else
        {
            logger.info("Getting the previously added addresses ");
            addressList = userDetailsDocument.getAddressDocument();
        }
        address.setAddressId(sequenceService.getNextIntSequence(AddressDocument.seqName));
        addressList.add(address);
        userDetailsDocument.setAddressDocument(addressList);
        userRepository.save(userDocument);
        logger.info("Inside addAddress method implementation:: end");
        return address;
    }
    /**
     * This method is used to getaddressList
     *
     * @return addresslist and Http Status
     */
    @Override
    public List<Address> getAddressList(String user_id) {
        logger.info("Inside getAddressList method implementation :: start");
        List<Address> addressDocumentList=new ArrayList<>();

        addressDocumentList= userDetailsDao.findUserById(user_id).getUserDetails().getAddressDocument();
        if(addressDocumentList==null)
        {
            logger.info("No address found throwing an exception");
            throw new AddressNotFoundException(Constants.NO_ADDRESS_FOUND);
        }
        logger.info("Inside addAddress method implementation :: end");
        return addressDocumentList;
    }
    /**
     * This method is used to update address
     *
     * @return Data String and Http Status
     */
    @Override
    public Address updateAddress(Address addressUpdate, int addressId, String userId)throws AddressNotFoundException
    {
        logger.info("Inside updateAddress method implementation :: start");
        UserDocument userDocument = userDetailsDao.findUserById(userId);
        addressList = userDocument.getUserDetails().getAddressDocument();
        Address address = addressList.stream().filter(a -> a.getAddressId() == addressId).findAny().orElse(null);
        if (null!=address) {
            logger.info("Checking for the fields entered by user to update");
            if(addressUpdate.getZipCode()!=' ')
            {
                address.setZipCode(addressUpdate.getZipCode());
            }
            if(addressUpdate.getCity()!=null)
            {
                address.setCity(addressUpdate.getCity());
            }
            if(addressUpdate.getCountry()!=null)
            {
                address.setCountry(addressUpdate.getCountry());
            }
            if(addressUpdate.getHouseNumber()!=null)
            {
                address.setHouseNumber(addressUpdate.getHouseNumber());
            }
            if(addressUpdate.getLandmark()!=null)
            {
                address.setLandmark(addressUpdate.getLandmark());
            }
            if(addressUpdate.getState()!=null)
            {
                address.setState(addressUpdate.getState());
            }
            if(address.getStreet()!=null)
            {
                address.setStreet(addressUpdate.getStreet());
            }
            userRepository.save(userDocument);
            logger.info("Inside addAddress method implementation:: end");
            return address;
        }
        logger.info("Inside addAddress method implementation:: end");
        return null;
    }
    /**
     * This method is used to delete address
     *
     * @return Data String and Http Status
     */
    @Override
    public Boolean deleteAddress(Integer address_id, String user_id) {
        logger.info("Inside deleteAddress method implementation :: start");
        UserDocument userDocument = userDetailsDao.findUserById(user_id);
        List<Address> addressList=userDocument.getUserDetails().getAddressDocument();
        Address addressToRemove = addressList.stream().filter(a -> a.getAddressId() == address_id).findAny().orElse(null);
        if (addressToRemove != null) {
            logger.info("Checking for address availability");
            addressList.remove(addressToRemove);
            userDocument.getUserDetails().setAddressDocument(addressList);
            userRepository.save(userDocument);
            logger.info("Inside deleteAddress method implementation:: end");
            return true;
        }
        logger.info("Inside addAddress method implementation:: end");
        return false;
    }
}
