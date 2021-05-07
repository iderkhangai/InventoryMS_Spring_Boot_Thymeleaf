package com.task.demo.repository;

import com.task.demo.model.Purchase;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PurchasesRepository extends MongoRepository<Purchase, String> {
}
