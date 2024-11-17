package org.example;

import ticket.Ticket;
import users.Admin;
import users.User;
import reader.FileReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        ticket.print();

        User client = new Admin();
        FileReader reader = new FileReader();

        List<Ticket> tickets  = reader.readTicketsFromFile("input.txt");
        for(Ticket item:tickets){
            item.print();
        }


    }
}
