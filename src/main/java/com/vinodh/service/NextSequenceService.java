package com.vinodh.service;

import com.vinodh.documents.CustomSequences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Service
public class NextSequenceService {

    @Autowired
    private MongoOperations mongoOperations;

    public long getNextSequence() {
        CustomSequences counter = mongoOperations.findAndModify(
                query(where("_id").is("customSequences")),
                new Update().inc("seq", 1),
                options().returnNew(true).upsert(true),
                CustomSequences.class);
        return counter.getSeq();
    }
}