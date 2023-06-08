package com.wiam.javasip;

public class OrderModal {
    // variables for our order,
    // coffeename, size and cream,price, id.
    private String coffeeName;
    private String size;
    private String cream;
    private String price;
    private int id;

    // creating getter and setter methods
    public String getCoffeeName() {
        return coffeeName;
    }
    public void setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public String getCream() {
        return cream;
    }
    public void setCream(String cream) {
        this.cream = cream;
    }
    public String getPrice() {
        return price;
    }

    public void setPrice(String price){
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
    // constructor
    public OrderModal(String coffeeName, String size, String cream, String price) {
        this.coffeeName = coffeeName;
        this.size = size;
        this.cream = cream;
        this.price = price;
    }
}

