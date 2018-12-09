package com.trivago.auth.repository;

import com.trivago.auth.domain.ApiPolicy;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiPolicyRepository extends MongoRepository<ApiPolicy, String> {

  Optional<ApiPolicy> findByApiKey(String apiKey);
}
