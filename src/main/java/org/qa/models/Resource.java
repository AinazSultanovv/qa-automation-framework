package org.qa.models;

public class Resource {
    private String name;
    private int quantity;

    public Resource(String name, int quantity){
        this.name = name;
        this.quantity = quantity;
    }
    public String getName(){return name;}
    public int getQuantity(){return quantity;}

    public void decrease(int amount){
        quantity = quantity - amount;
        if (quantity < 0){
            throw new IllegalArgumentException("quantity < 0");
        }
    }
}
