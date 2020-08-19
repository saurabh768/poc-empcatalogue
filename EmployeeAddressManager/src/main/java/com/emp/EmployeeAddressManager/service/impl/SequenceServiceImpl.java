package com.emp.EmployeeAddressManager.service.impl;

import com.emp.EmployeeAddressManager.document.IntSequence;
import com.emp.EmployeeAddressManager.document.Sequence;
import com.emp.EmployeeAddressManager.service.SequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
public class SequenceServiceImpl implements SequenceService {


    MongoOperations mongoOperations;

    @Autowired
    public void setMongoOperations(MongoOperations mongoOperations)
    {
        this.mongoOperations=mongoOperations;
    }

    public long getNextSequence(String key)
    {
        Sequence sequence=mongoOperations.findAndModify(query(where("_id").is(key)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                Sequence.class);
        return !Objects.isNull(sequence) ? sequence.getSeq() : 1;
    }

    public int getNextIntSequence(String key)
    {
        IntSequence intSequence=mongoOperations.findAndModify(query(where("_id").is(key)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                IntSequence.class);
        return !Objects.isNull(intSequence) ? intSequence.getSeq() : 1;
    }
    public void resetSequence(String key)
    {
        mongoOperations.findAndRemove(query(where("_id").is(key)),Sequence.class);

    }
    public String userSequence(String key)
    {
        long nextSequence=getNextSequence(key);
        String stringId="B200"+nextSequence;
        return stringId;
    }


}

