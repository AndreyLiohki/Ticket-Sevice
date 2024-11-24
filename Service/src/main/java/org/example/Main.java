package org.example;

import model.ticket.Ticket;
import model.users.Admin;
import model.users.User;
import reader.FileReader;

import java.util.List;

public class Main {
    private static final String filePath = "input.txt";
    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        ticket.print();

        User client = new Admin();
        FileReader reader = new FileReader();

        List<Ticket> tickets  = reader.readTicketsFromFile(filePath);
        for(Ticket item:tickets){
            item.print();
        }


    }
}
