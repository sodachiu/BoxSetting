package com.example.eileen.boxsetting;

public class Resolution {
    private String name;
    private int id;
    private boolean ischecked = false;

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

    public void setIschecked(boolean ischecked) {
        this.ischecked = ischecked;
    }

    public boolean getIsChecked(){
        return this.ischecked;
    }
}
