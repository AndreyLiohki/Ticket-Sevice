package org.example;

import Senders.EmailSender;
import Senders.TelSender;
import Senders.Sender;
import TIcket.Ticket;
import Users.Admin;
import Users.Client;
import Users.User;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Main implements SetClassId, classContent {
    private static int classID;

    public static void main(String[] args) {

        Ticket ticket = new Ticket();
        ticket.setId(4739);
        System.out.println(ticket.getId());
        System.out.println();

        ticket.SetTicketDay(LocalDate.now());

        try(FileWriter writer = new FileWriter("info.txt")){
            ticket.print(writer);
        } catch (IOException e) {
            System.out.println("There is no possibility to create output file");
        }

        ticket.print();
        System.out.println();

        Sender sender = new TelSender();
        sender.shared(ticket);
        System.out.println();

        Sender sender2= new EmailSender();
        sender2.shared(ticket);
        System.out.println();

        User user = new Admin();
        user.PrintRole();
        System.out.println();

        User user2 = new Client();
        user2.PrintRole();
    }

    @Override
    public void print(){

        System.out.println("Class Id: " + classID);
    }

    @Override
    public void print(FileWriter file) {
        if (file == null) {
            throw new IllegalArgumentException("FileWriter cannot be null");
        }

        try {
            file.write("Class ID: " + classID);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
