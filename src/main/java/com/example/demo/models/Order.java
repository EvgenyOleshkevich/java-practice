package com.example.demo.models;


public class Order {
    private long id;

    private String item;

    private String seller;

    private String address;

    public Order(Variable var){
        id = var.getID();
        item = var.getName().split("_")[0];
        seller = var.getSeller();
        address = var.getAddress();
    }

    public long getID() {
        return id;
    }

    public void setID(long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getSeller() {
        return seller;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }
}
