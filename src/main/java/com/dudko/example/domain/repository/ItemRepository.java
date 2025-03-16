package com.dudko.example.domain.repository;

import com.dudko.example.domain.entity.ItemEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ItemRepository extends MongoRepository<ItemEntity, UUID> {
}
