package com.emp.EmployeeAddressManager.resource;

import com.emp.EmployeeAddressManager.config.JwtTokenUtil;
import com.emp.EmployeeAddressManager.exception.AddressNotFoundException;
import com.emp.EmployeeAddressManager.exception.TokenNotMatchedException;
import com.emp.EmployeeAddressManager.model.Address;
import com.emp.EmployeeAddressManager.service.AddressService;
import com.emp.EmployeeAddressManager.utils.Constants;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/empmanager/address")
public class AddressResource {

    private static final Logger logger = LoggerFactory.getLogger(AddressResource.class);

    @Autowired
    AddressService addressService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping(value = "/add/{user_id}")
    public ResponseEntity<String> addAddress(HttpServletRequest request, @RequestBody Address address,@PathVariable("user_id") String user_id) {
        logger.info("START:: Inside addAddress method ");
        String requestTokenHeader = request.getHeader("Authorization");
        String tokenUserId = jwtTokenUtil.getUsernameFromToken(requestTokenHeader.substring(7));
        if (tokenUserId.equalsIgnoreCase(user_id)) {
            logger.info("Validating security token");
            // Address addressDocument = new Address();
            Address address1 = addressService.addAddress(address, user_id);
            logger.info("END:: Inside addAddress method ");
            logger.info("Updated Address :: " + address1);
            return new ResponseEntity<>(Constants.ADDRESS_ADDED, HttpStatus.OK);
        } else {
            logger.info("Token is not valid");
            logger.info("Inside addAddress method :: end");
            throw new TokenNotMatchedException(Constants.USER_NOT_LOGGED_IN);
        }
    }
    /**
     * This method is used to getAddressList.
     *
     * @return addresses stored and Http Status
     */
    @GetMapping(value = "/getAddressList/{user_id}")
    public ResponseEntity<Object> getAddressList(HttpServletRequest request, @PathVariable("user_id") String user_id) {
        logger.info("Inside getAddressList method :: start");
        String requestTokenHeader = request.getHeader("Authorization");
        String tokenUserId = jwtTokenUtil.getUsernameFromToken(requestTokenHeader.substring(7));
        List<Address> addressList;
        if (tokenUserId.equalsIgnoreCase(user_id)) {
            logger.info("Validating security token");
            addressList = addressService.getAddressList(user_id);
            logger.info("Inside getAddressList method :: end");
            if (addressList.size() == 0)
                return new ResponseEntity<Object>(Constants.NO_ADDRESS_FOUND, HttpStatus.NOT_FOUND);
            else
                return new ResponseEntity<Object>(addressList, HttpStatus.OK);

        } else {
            logger.info("Token is not valid");
            logger.info("Inside getAddressList method :: end");
            throw new TokenNotMatchedException(Constants.USER_NOT_LOGGED_IN);
        }
    }

    /**
     * This method is used to update Address.
     *
     * @return Data String and Http Status
     */
    @PutMapping(value = "/update/{address_id}/{user_id}")
    public ResponseEntity<Object> updateAddress(HttpServletRequest request, @RequestBody Address addressUpdate, @PathVariable("address_id") int addressId,@PathVariable("admin_id") String admin_id, @PathVariable("user_id") String user_id) {
        logger.info("Inside updateAddress method ::start");
        String requestTokenHeader = request.getHeader("Authorization");
        String tokenUserId = jwtTokenUtil.getUsernameFromToken(requestTokenHeader.substring(7));
        if (tokenUserId.equalsIgnoreCase(user_id)) {
            logger.info("Validating security token");
            if (addressService.updateAddress(addressUpdate, addressId, user_id) != null) {
                logger.info("Updating address");
                return new ResponseEntity<Object>(Constants.ADDRESS_UPDATED, HttpStatus.OK);
            } else {
                logger.info("Given Address not found");
                return new ResponseEntity<Object>(Constants.ADDRESS_NOT_UPDATED, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            logger.info("Token is not valid");
            logger.info("Inside updateAddress method :: end");
            throw new TokenNotMatchedException(Constants.USER_NOT_LOGGED_IN);
        }

    }

    /**
     * This method is used to  delete Address.
     *
     * @return Data String and Http Status
     */
    @DeleteMapping(value = "/delete/{address_id}/{user_id}")
    public ResponseEntity<Object> deleteAddress(HttpServletRequest request, @PathVariable("address_id") Integer address_id,@PathVariable("admin_id") String admin_id, @PathVariable("user_id") String user_id) {
        logger.info("Inside deleteAddress method :: start");
        String requestTokenHeader = request.getHeader("Authorization");
        String tokenUserId = jwtTokenUtil.getUsernameFromToken(requestTokenHeader.substring(7));
        if (tokenUserId.equalsIgnoreCase(user_id)) {
            logger.info("Validating security token");
            if ((addressService.deleteAddress(address_id, user_id)) == true) {
                logger.info("Address Deleted");
                return new ResponseEntity<>(Constants.ADDRESS_DELETE, HttpStatus.OK);
            } else {
                logger.info("Given address is not found");
                throw new AddressNotFoundException(Constants.NO_ADDRESS_FOUND);
            }
        } else {
            logger.info("Token is not valid");
            logger.info("Inside deleteAddress method :: end");
            throw new TokenNotMatchedException(Constants.USER_NOT_LOGGED_IN);
        }
    }

}
