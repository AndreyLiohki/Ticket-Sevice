package org.example;

import SendingTheTelephone.TicketSending;
import TIcket.Ticket;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import SendingTheTelephone.TelephoneSending;
import SendingTheTelephone.EmailSending;
import Users.*;

public class TicketService implements SetClassId, ClassInfo {
    private static int classID;
    public static void main(String[] args) {

        Ticket ticket = new Ticket();
        ticket.SetId(4739);
        System.out.println(ticket.GetId());
        System.out.println();

        ticket.SetTicketDay(LocalDate.now());

        try(FileWriter writer = new FileWriter("info.txt")){
            ticket.print(writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
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
    public void SetId(int id){
        classID = id;
    }

    @Override
    public int GetId(){
        return classID;
    }
}