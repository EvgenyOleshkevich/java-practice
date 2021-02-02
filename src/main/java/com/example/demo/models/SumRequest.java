package com.example.demo.models;

public class SumRequest {

    private String first;
    private String second;

    public SumRequest(){}

    public SumRequest(String first, String second) {
        this.first = first;
        this.second = second;
    }

    public String getFirst() { return first; }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getSecond() { return second; }

    public void setSecond(String second) {
        this.second = second;
    }
}
