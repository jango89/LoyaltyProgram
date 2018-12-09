package com.trivago.catalogue.repository;

import com.trivago.catalogue.domain.Item;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {

  List<Item> findAllByQuantityAvailableGreaterThan(int quantityAvailable, PageRequest pageRequest);

  Optional<Item> findByIdAndQuantityAvailableGreaterThanEqual(String id, Integer quantity);
}
