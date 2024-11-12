package org.example;

import TIcket.Ticket;
import Users.Admin;
import Users.User;

import java.io.FileWriter;
import java.io.IOException;

public class Main extends SetClassId implements Print {
    private static int classID;

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        ticket.print();

        User client = new Admin();

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
