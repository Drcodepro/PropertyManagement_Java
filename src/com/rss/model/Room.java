package com.rss.model;

public class Room {

    public boolean isAvailable;
    public int price;
    public String location;

    public Room(int price,String location,boolean isAvailable){
        this.price = price;
        this.location = location;
        this.isAvailable = isAvailable;
    }
}
