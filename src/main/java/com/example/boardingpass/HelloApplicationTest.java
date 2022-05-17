package com.example.boardingpass;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

class HelloApplicationTest {

    @Test
    void start() {
        Location city = new Location("LAX","Los Angeles,CA",326,1000);
        Person p = new Person("John Smith","example@email.com","5555555555",false,67);
        Person p3 = new Person("Jack Smith","example@email.com","5555555555",false,25);
        Person p1 = new Person("Jane Smith","example@email.com","5555555555",true,30);
        Person p2 = new Person("Joe Smith","example@email.com","5555555555",false,5);
        Calendar c = Calendar.getInstance();
        c.set(2022,Calendar.MAY,25,20,0);
        Ticket t = new Ticket(c,city,p);
        Ticket t1 = new Ticket(c,city,p1);
        Ticket t2 = new Ticket(c,city,p2);
        Ticket t3 = new Ticket(c,city,p3);
        assertEquals(750,t1.price);
        assertEquals(city.basePrice*0.4,t.price);
        assertEquals(city.basePrice*0.5,t2.price);
        assertEquals(city.basePrice,t3.price);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy-HH:mm");
        c.set(2022,Calendar.MAY,26,1,26);
        assertEquals(dateFormat.format(c.getTime()),dateFormat.format(t.eta.getTime()));
    }
}