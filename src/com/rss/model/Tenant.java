package com.rss.model;

public class Tenant {
    public String name;
    public String phone;
    public String address;
    public boolean isActice = true;

    public Tenant(String name,String phone,String address){
        this.name = name;
        this.phone = phone;
        this.address = address;
    }
}
