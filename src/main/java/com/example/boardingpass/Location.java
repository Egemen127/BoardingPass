package com.example.boardingpass;/*
Constructor Parameters
String code- 3 letter airport code
String name- the city name where the airport is
int duration- duration of the flight from JFK in minutes
double price- price of the flight without any discount applied
*/
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
    public Location(){

    }

    @Override
    public String toString() {
        return code+ " " + name + ", BasePrice=" +" $"+ (int)basePrice;
    }
}
