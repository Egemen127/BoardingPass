package com.example.boardingpass;

public class Location {
    public String code;
    public String name;
    public int duration;
    public double basePrice;
    public Location(String code,String name,int duration,double price){
        this.code = code;
        this.name = name;
        this.duration = duration;
        this.basePrice = price;
    }
}
