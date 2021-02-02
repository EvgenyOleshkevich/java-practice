package com.example.demo.models;

//import org.hibernate.annotations.Entity;


public class SumResponse {

    private String description;

    private  int sum;

    private  int code;

    public SumResponse(){}

    public SumResponse(int sum, int code, String description) {
        this.description = description;
        this.sum = sum;
        this.code = code;
    }

    public int getSum() { return sum; }

    public void setSum(int sum) { this.sum = sum; }

    public int getCode() { return code; }

    public void setCode(int code) { this.code = code; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
