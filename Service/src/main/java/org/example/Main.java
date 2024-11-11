package org.example;

import TIcket.Ticket;
import Users.Admin;
import Users.User;
import Reader.ReadFile;

import java.util.ArrayList;
import java.util.List;

public class Main extends printContent implements SetClassId {
    private static int classID;

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        ticket.print();

        User client = new Admin();
        ReadFile reader = new ReadFile();

        List<Ticket> tickets  = reader.readTicketsFromFile("input.txt");
        for(Ticket item:tickets){
            item.print();
        }


    }

    @Override
    public void print(){

        System.out.println("Class Id: " + classID);
    }

    @Override
    public void setId(int id){
        classID = id;
    }

    @Override
    public int getId(){
        return classID;
    }
}
