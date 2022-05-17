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
        //To avoid calculating the durations, I assume that every flight departs from one location
        //so each location has a flight duration attribute
        Location city = new Location("fdg","cityine",180,500);
        Person p = new Person("ex","eee@email","333333",true,35);
        Calendar c = Calendar.getInstance();
        c.set(2020,Calendar.MAY,6,21,0);
        Ticket t = new Ticket(c,city,p);

        t.setPrice();//375.0
        //SimpleDateFormat class formats the time as how we want to display it
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy-HH:mm");
        System.out.println(dateFormat.format(t.time.getTime()));//05-06-2020-21:00
        System.out.println(dateFormat.format(t.setETA().getTime()));//05-07-2020-00:00
        System.out.println(dateFormat.format(t.eta.getTime()));//05-07-2020-00:00
        System.out.println(dateFormat.format(t.time.getTime()));//05-06-2020-21:00
        System.out.println(t.price);//375.0 as expected the female customer (gender=true means female) gets a 25% discount on the ticket

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