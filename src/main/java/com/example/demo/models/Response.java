package com.example.demo.models;

import com.example.demo.CodeResponse;

public class Response {
    private String description;

    private  int code;

    private  int value;

    public Response(){}

    public Response(int value, int code, String description) {
        this.description = description;
        this.value = value;
        this.code = code;
    }

    public Response(int value, CodeResponse code) {
        this.description = code.toString();
        this.value = value;
        this.code = code.getCode();
    }

    public int getCode() { return code; }

    public void setCode(int code) { this.code = code; }

    public int getValue() { return value; }

    public void setValue(int value) { this.value = value; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
