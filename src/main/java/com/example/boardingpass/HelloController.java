package com.example.boardingpass;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.stream.Collectors;

public class HelloController {
    //Reads the location information from the file and stores them in an arraylist
    final ArrayList<Location> LOCATIONS = Files.readAllLines(Path.of("locations.txt")).stream()
            .map(e-> e.split("\\|"))
            .map(e->new Location(e[0],e[1],Integer.parseInt(e[2]),Integer.parseInt(e[3])))
            .collect(Collectors.toCollection(ArrayList::new));
    //Stores the arraylist elements in an observable list
    ObservableList<Location> items = FXCollections.observableArrayList(LOCATIONS);
    //Declaring the gui elements
    @FXML
    private TextField nameLabel;
    @FXML
    private TextField emailLabel;
    @FXML
    private TextField ageLabel;
    @FXML
    private TextField numberLabel;
    @FXML
    private ImageView img;
    @FXML
    private Label error;
    @FXML
    private DatePicker timeSelect;
    @FXML
    private RadioButton radioMale;
    @FXML
    private ToggleGroup Gender;
    @FXML
    private ChoiceBox<Location> loc;
    @FXML
    private TextField hour;
    @FXML
    private TextField min;
    @FXML
    private Label priceLabel;
    @FXML
    void initialize(){
        //setting up the choice box
        loc.setValue(items.get(0));//default value is LAX
        loc.setItems(items);//adding the observable list objects
        img.setImage(new Image("file:src/plane.jpg"));//setting the background image
        img.setOpacity(0.4);
        timeSelect.setValue(LocalDate.now());//sets the date to today's date

    }

    public HelloController() throws IOException {
    }
    @FXML
    protected boolean SendForm() throws IOException {
    //Validates the form, if the form is valid stores it and generates a ticket
    //If the form is not valid, shows an error message to the user
    error.setTextFill(Color.RED);
    String name = nameLabel.getText();
    String email = emailLabel.getText();
    String[] date = timeSelect.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE).split("-");
    String number = numberLabel.getText();
    boolean gender = !Gender.getSelectedToggle().equals(radioMale);
    int hr;
    int mn;
    int age;
    //Checks if the hour and minute are entered correct
    try{
    hr= Integer.parseInt(hour.getText())%24;
    mn=Integer.parseInt(min.getText())%60;
    }catch(Exception e){//error message is generated when an exception is caught
        error.setText("Enter an integer between 0-23 for hour, 0-59 for minute");
        return false;
    }
    //Checks the age
    try{
    age = Integer.parseInt(ageLabel.getText());
    } catch(Exception e){
        error.setText("Please enter an Integer greater than 0 for age.");
        return false;
    }
    //ensures input email,number and name input fields are not empty
    if(name.isEmpty()||email.isEmpty()||number.isEmpty()){
        error.setText("Please fill all of the input fields.");
        return false;
    } else if(age<=0) {
        error.setText("Please enter an Integer greater than 0 for age.");
        return false;
    } else if(date.length==0){//checks the date field
        error.setText("Please Enter a Date.");
        return false;}
    //After validating all the input fields, it's time to generate the ticket
    //Setting the parameters for the ticket constructor
    Person p = new Person(name,email,number,gender,age);
    //Sets up the calendar according to the departure time input
    Calendar c = Calendar.getInstance();
    c.set(Integer.parseInt(date[0]),Integer.parseInt(date[1])-1,Integer.parseInt(date[2]),hr,mn);
    Location l = loc.getValue();

    Ticket t = new Ticket(c,l,p);//Generating ticket object
    t.recordTicket();//Stores the info in tickets.txt file
    Ticket.printTicket(t.id.toString()); //Prints ticket in tickets folder

    //sets all the input fields to default
    ageLabel.setText("");
    nameLabel.setText("");
    emailLabel.setText("");
    numberLabel.setText("");
    priceLabel.setText("Price:");
    timeSelect.setValue(LocalDate.now());
    loc.setValue(LOCATIONS.get(0));
    hour.setText("");
    min.setText("");


    error.setTextFill(Color.GREEN);
    error.setText("Ticket Generated Successfully!");
    return true;
    }
    @FXML
    protected void calculatePrice(){
        //Calculates the ticket price according to age, gender and destination
        try{double price = Ticket.calcPrice(loc.getValue().basePrice,Integer.parseInt(ageLabel.getText()),!Gender.getSelectedToggle().equals(radioMale));
        priceLabel.setText("Price: $"+price);} catch (Exception e){
            priceLabel.setText("Invalid Input");
        }
    }
}