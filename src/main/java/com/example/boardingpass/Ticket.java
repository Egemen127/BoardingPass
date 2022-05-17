package com.example.boardingpass;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
this.passenger = pass;
int age = pass.age;
double price = this.destination.basePrice;
//sets the price in the ticket according to age and gender
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
    //sets the eta
    Calendar a = Calendar.getInstance();
    a.setTime(this.time.getTime());
    a.add(Calendar.MINUTE,this.destination.duration);
    this.eta = a;
}

public void recordTicket() throws IOException {
    File my_file = new File("tickets.txt");
    Writer output = new BufferedWriter(new FileWriter(my_file, true));
    String passinfo = this.passenger.name+"|"+this.passenger.email+"|"+this.passenger.number+"|"+this.passenger.gender+"|"+this.passenger.age+"|";
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy-HH:mm");
    String ticketinfo = this.destination.code+"|"+this.destination.name+"|"+dateFormat.format(this.time.getTime())+"|"+dateFormat.format(this.eta.getTime())+"|"+this.price+"|"+this.id;
    output.append(passinfo+ticketinfo+"\n");
    output.close();
}
public static void printTicket(String id) throws IOException {

    String[] ticket = get_ticket(id);
    //Checks if such a ticket exists
    if(ticket.length==1)
        System.out.println("The ticket with given id was not found.");
    else {//Parses the information and stores it in a text file
        File my_file = new File("tickets/"+id+".txt");
        Writer output = new BufferedWriter(new FileWriter(my_file, true));
        String gender = "Male";
        if (ticket[3].equals("true"))
            gender = "Female";
        output.append(String.format(""" 
                                    Genspark Airlines 
                Ticket Information                       Personal Details
                                     
                From: JFK                                Name: %s
                Destination: %s                         Email: %s             
                Departure Time: %s EST     Number: %s             
                ETA: %s EST                Gender: %s
                                                         Age: %s         
                            
                This ticket is valid for either direction
                """, ticket[0], ticket[5], ticket[1], ticket[7], ticket[2],ticket[8], gender,ticket[4]));
        output.append(String.format("Your Unique ticket id is %s\n" +
                "Thank you for flying with Genspark Airlines", ticket[10]));
        output.close();
    }}
//returns info of the ticket with given id in a string array
public static String[] get_ticket(String id) throws IOException {
    String a = Files.readAllLines(Path.of("tickets.txt")).stream()
            .filter(e-> e.contains(id)).reduce("",(f, b)->b+f);
    return a.split("\\|");
    }

}
