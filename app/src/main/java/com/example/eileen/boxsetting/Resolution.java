package com.example.eileen.boxsetting;

public class Resolution {
    private String name;
    private int id;

    public Resolution(String name, int id){
        this.name = name;
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public int getId(){
        return this.id;
    }

}
