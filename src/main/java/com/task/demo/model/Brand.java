package com.task.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "brand")
public class Brand {
    @Id
    private String id;
    private String name;
    private Integer status;
}
