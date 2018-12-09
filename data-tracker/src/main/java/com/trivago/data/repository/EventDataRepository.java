package com.trivago.data.repository;

import com.trivago.data.domain.EventData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface
EventDataRepository extends MongoRepository<EventData, String> {

}
