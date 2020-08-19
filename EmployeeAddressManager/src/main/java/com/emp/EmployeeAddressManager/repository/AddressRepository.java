package com.emp.EmployeeAddressManager.repository;


import com.emp.EmployeeAddressManager.document.AddressDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AddressRepository extends MongoRepository<AddressDocument,String> {

    @Query("{'user_id':?0}")
    List<AddressDocument> getAddressListbyUserId(String userId);

}
