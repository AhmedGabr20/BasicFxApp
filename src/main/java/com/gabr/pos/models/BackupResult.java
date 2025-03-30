package com.gabr.pos.models;

public class BackupResult {

    int id ;
    String name ;
    String data ;
    String result ;
    String icon ;

    public BackupResult(String name, String data, String result, String icon) {
        this.name = name;
        this.data = data;
        this.result = result;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
