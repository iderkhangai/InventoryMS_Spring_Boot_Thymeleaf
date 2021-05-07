package com.task.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "supplier")
public class Supplier {
    @Id
    private String id;
    private String name;
    private String address;
    private String phone;
}
