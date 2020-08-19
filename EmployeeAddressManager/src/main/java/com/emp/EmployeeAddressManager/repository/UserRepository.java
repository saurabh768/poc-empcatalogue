package com.emp.EmployeeAddressManager.repository;


import com.emp.EmployeeAddressManager.document.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends MongoRepository<UserDocument,Integer> {


    @Query("{'email':?0}")
    UserDocument findByEmail(String email);

    @Query("{'userId':?0}")
    UserDocument findById(String user_id);

}
