package com.example.boardingpass;
import java.time.*;


public class Person {
    public String name;
    public String email;
    public String number;
    public boolean gender;
    public Location destination;
    public LocalDateTime departure;
    public Person(String name,String email,String number,boolean gender,Location dest,LocalDateTime dep){
        this.name = name;
        this.email = email;
        this.number = number;
        this.gender = gender;
        this.destination = dest;
        this.departure = dep;
    }
}
