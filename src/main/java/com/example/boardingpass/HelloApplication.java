package com.example.boardingpass;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //Initializing some random data to test the functions
        //To avoid calculating the durations, I assume that every flight departs from the same location
        //so each location has a flight duration in minutes attribute
        Location city = new Location("LAX","Los Angeles,CA",326,1000);
        Person p = new Person("John Smith","example@email.com","5555555555",false,67);
        Calendar c = Calendar.getInstance();
        c.set(2022,Calendar.MAY,25,20,0);
        Ticket t = new Ticket(c,city,p);
        t.recordTicket();
        //SimpleDateFormat class formats the time as how we want to display it
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy-HH:mm");
        System.out.println(dateFormat.format(t.time.getTime()));//05-06-2020-21:00
        System.out.println(dateFormat.format(t.eta.getTime()));//05-07-2020-00:00
        System.out.println(dateFormat.format(t.time.getTime()));//05-06-2020-21:00
        System.out.println(t.price);//375.0 as expected the female customer (gender=true means female) gets a 25% discount on the ticket
        Ticket.printTicket("f0476f58-5810-49dc-a461-b8dd57a87248");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}