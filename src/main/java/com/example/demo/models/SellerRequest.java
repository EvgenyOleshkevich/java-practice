package com.example.demo.models;
//import org.hibernate.annotations.Entity;

public class SellerRequest {
    private String login;

    private  String description;

    public SellerRequest(String login, String description) {
        this.login = login;
        this.description = description;
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
}
