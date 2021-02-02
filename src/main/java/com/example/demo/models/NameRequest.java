package com.example.demo.models;

//import org.hibernate.annotations.Entity;


public class NameRequest {

    private String name;

    public NameRequest(){}

    public NameRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
