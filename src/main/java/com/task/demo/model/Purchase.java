package com.task.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "order")
public class Purchase {
    @Id
    private String orderId;
    private String customerId;
    private Date orderDate;
    private Integer status;

}
