package com.moodys.ma.ds.repositories;

import com.moodys.ma.ds.entities.LegalEntitiesRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LegalEntitiesRepository extends MongoRepository<LegalEntitiesRecord, String> {


    @Query(value = "{ '_id' : ?0 }")
    LegalEntitiesRecord findByEntityId(String id);

}
