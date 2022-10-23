package com.example.entity;

public class WebInfo {

    private String ID, Keyword, Value;
    private Boolean Status;

    public WebInfo() {

    }

    public WebInfo(String iD, String keyword, String value, Boolean status) {
        ID = iD;
        Keyword = keyword;
        Value = value;
        Status = status;
    }

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public String getKeyword() {
        return Keyword;
    }

    public void setKeyword(String keyword) {
        Keyword = keyword;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }

}
