package com.example.phase5;

public class Singleton  {


    private Singleton(){}

    private static final Singleton instance = new Singleton();

    private String text_to_Change;

    public static Singleton getInstance(){
        return instance;
    }

    public void setText_to_Change(String text){
        this.text_to_Change = text;
    }

    public String getText_to_Change(){
        return text_to_Change;
    }

}
