package com.task.demo.repository;

import com.task.demo.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    @Query(value = "{ name: $0 },{ $inc: { quantity: 1000 }")
    public void updateId(String productId);

}
