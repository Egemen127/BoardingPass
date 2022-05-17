package com.example.boardingpass;


public class Person {
    public String name,email,number;
    public boolean gender;
    public int age;
    public Person(String name,String email,String number,boolean gender,int age){
        this.age = age;
        this.name = name;
        this.email = email;
        this.number = number;
        this.gender = gender;

    }
}
