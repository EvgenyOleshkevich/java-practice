package com.example.demo.models;

//import org.hibernate.annotations.Entity;

import javax.persistence.Id;
import javax.persistence.Entity;

@Entity
public class Variable {
    @Id
    private String name;

    private  int value;

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
