package com.example.demo;

public enum CodeResponse{
    Ok(0),
    InvalidRequest(1),
    NoSuchRequestType(2),
    JSONFormatError(3),
    VariableAlreadyExist(4),
    VariableDontExist(5),
    CouldNotSaveObject(6);

    private int code;
    CodeResponse(int code){
        this.code = code;
    }
    public int getCode(){ return code;}
}
