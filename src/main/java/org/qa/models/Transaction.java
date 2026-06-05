package org.qa.models;

import java.time.LocalDateTime;

public class Transaction {
    private String id;
    private String userId;
    private double amount;
    private String status;
    private LocalDateTime timestamp;



    public Transaction(String id, String userId, double amount, String status, LocalDateTime timestamp){
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.status =  status;
        this.timestamp = timestamp;
    }
    public String getId(){return id;}
    public String getUserId(){return userId;}
    public double getAmount(){return amount;}
    public String getStatus(){return status;}
    public LocalDateTime getTimestamp(){return timestamp;}

    public void setStatus(String status){
        this.status = status;
    }
}
