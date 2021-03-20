package com.example.demo.models;

import com.example.demo.CodeResponse;

public class Response {
    private String description;

    private int code;

    private long value;

    public Response(){}

    public Response(long value, int code, String description) {
        this.description = description;
        this.value = value;
        this.code = code;
    }

    public Response(long value, CodeResponse code) {
        this.description = code.toString();
        this.value = value;
        this.code = code.getCode();
    }

    public int getCode() { return code; }

    public void setCode(int code) { this.code = code; }

    public long getValue() { return value; }

    public void setValue(long value) { this.value = value; }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
