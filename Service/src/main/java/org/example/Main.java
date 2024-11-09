package org.example;

import SendingTheTelephone.EmailSending;
import SendingTheTelephone.TelephoneSending;
import SendingTheTelephone.TicketSending;
import TIcket.Ticket;
import Users.Admin;
import Users.Client;
import Users.User;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class Main {
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

        TicketSending sender = new TelephoneSending();
        sender.shared(ticket);
        System.out.println();

        TicketSending sender2= new EmailSending();
        sender2.shared(ticket);
        System.out.println();

        User user = new Admin();
        user.PrintRole();
        System.out.println();

        User user2 = new Client();
        user2.PrintRole();

    }
}
