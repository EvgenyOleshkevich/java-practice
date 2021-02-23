package com.example.demo.models;
//import org.hibernate.annotations.Entity;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Customer {
    @Id
    private String login;

    private  String password;

    private  int amount;

    public Customer(){}

    public Customer(String login, String password) {
        this.login = login;
        this.password = password;
        this.amount = 0;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
