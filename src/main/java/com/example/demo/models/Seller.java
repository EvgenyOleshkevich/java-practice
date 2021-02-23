package com.example.demo.models;
//import org.hibernate.annotations.Entity;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Seller {
    @Id
    private String login;

    private  String password;

    private  String description;

    private  int amount;

    public Seller(){}

    public Seller(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(int value) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String name) {
        this.login = login;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
