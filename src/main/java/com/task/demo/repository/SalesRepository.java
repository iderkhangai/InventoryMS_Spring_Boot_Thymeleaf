package com.task.demo.repository;

import com.task.demo.model.Sales;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface SalesRepository extends MongoRepository<Sales, String> {
    @Query("{ 'status' : 'Pending' }")
    public List<Sales> getPendingProducts();
}
