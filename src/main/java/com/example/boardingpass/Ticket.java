package com.example.boardingpass;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Ticket {
public UUID id;
public Calendar time;
public Location destination;
public Calendar eta;
public Person passenger;
public double price;
Ticket(Calendar time,Location dest,Person pass){
    //UUID generates a random ticket id for each ticket
this.id = UUID.randomUUID();
this.time = time;
this.destination = dest;
this.passenger = pass;}
//sets the price according to age and gender, returns the adjusted price
public double setPrice(){
    int age = this.passenger.age;
    double price = this.destination.basePrice;
    if(age<=12){
        this.price =  price*0.5;
    }else
        if(age>=60){
            this.price = price*0.4;
        } else
            if(passenger.gender){
            this.price = price*0.75;
        } else
            this.price = price;
            return this.price;
}
//sets the eta and returns the eta
public Calendar setETA(){
    Calendar a = Calendar.getInstance();
    a.setTime(this.time.getTime());
    a.add(Calendar.MINUTE,this.destination.duration);
    this.eta = a;
    return a;
}

}
