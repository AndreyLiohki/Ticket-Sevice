package org.example;

import senders.EmailSender;
import senders.TelephoneSender;
import ticket.Ticket;
import users.Admin;
import users.Client;
public class Main{

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        ticket.print();
        System.out.println();

        ticket.setId(1);
        System.out.println("Ticket class ID: " + ticket.getId());
        System.out.println();

        Admin admin = new Admin();
        admin.printRole();
        admin.checkTicket(ticket);
        System.out.println();

        Client user = new Client();
        user.printRole();
        user.getTicket();
        System.out.println();

        TelephoneSender sender = new TelephoneSender();
        sender.share(ticket);
        sender.setId(3);
        System.out.println("Sender class ID: " + sender.getId());
        System.out.println();


        EmailSender mailSender = new EmailSender();
        mailSender.share(ticket);
        mailSender.setId(4);
        System.out.println("Sender class ID: " + mailSender.getId());

    }

}
