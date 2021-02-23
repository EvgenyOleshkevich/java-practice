package com.example.demo.models;

//import org.hibernate.annotations.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Variable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long item;

    private String seller;

    private String address;

    public long getID() {
        return id;
    }

    public void setID(long id) {
        this.id = id;
    }

    public long getItem() {
        return item;
    }

    public void setItem(long item) {
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

    private String name;

    private  int value;

    public Variable(){}

    public Variable(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
