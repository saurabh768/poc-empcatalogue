package com.empmanager.empcatalogue.service.impl;

import com.empmanager.empcatalogue.model.Address;
import com.empmanager.empcatalogue.model.JwtResponseWithUsername;
import com.empmanager.empcatalogue.model.LoginDetails;
import com.empmanager.empcatalogue.service.EmpAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class EmpAddressServiceImpl implements EmpAddressService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public List<Address> getAddressList(String username,String password)
    {
        String loginUrl="http://EMP-ADDRESS-MANAGER-SERVICE/empmanager/user/login";
        HttpEntity<LoginDetails> request=new HttpEntity<>(new LoginDetails(username,password));
        ResponseEntity<JwtResponseWithUsername> jwtResponseEntity=restTemplate.exchange(loginUrl,HttpMethod.POST,request,JwtResponseWithUsername.class) ;
        JwtResponseWithUsername jwtResponse=jwtResponseEntity.getBody();
        String getAddressListUrl="http://EMP-ADDRESS-MANAGER-SERVICE/empmanager/address/getAddressList/"+jwtResponse.getUser_id();
        HttpHeaders headers=new HttpHeaders();
        headers.set("Authorization","Bearer "+jwtResponse.getToken());
        HttpEntity getRequest=new HttpEntity(headers);

        ResponseEntity<Object> addressListEntity=restTemplate.exchange(getAddressListUrl, HttpMethod.GET,getRequest,Object.class);
        if(addressListEntity.getStatusCode()== HttpStatus.OK) return (List<Address>) addressListEntity.getBody();
        return null;
    }
}
