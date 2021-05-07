package com.task.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.function.DoubleUnaryOperator;

@Data
@Document(collection = "sales")
public class Sales {
    @Id
    private String id;
    private String productId;
    private String salecode;
    private String supplierId;
    private String status;
    private String paymentStatus;
    private String date;
    private Double amount;
    private String note;
    private User user;
    private Double due;
    private Double grandTotal;
    public List<Product> products;

    @Override
    public String toString() {
        return "Sales{" +
                "id='" + id + '\'' +
                ", productId='" + productId + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", status='" + status + '\'' +
                ", paymentStatus='" + paymentStatus + '\'' +
                ", date='" + date + '\'' +
                ", amount=" + amount +
                ", note='" + note + '\'' +
                ", due=" + due +
                ", grandTotal=" + grandTotal +
                ", products=" + products +
                '}';
    }
}
