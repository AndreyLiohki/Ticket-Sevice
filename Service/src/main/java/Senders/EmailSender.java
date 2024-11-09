package Senders;

import TIcket.Ticket;

import java.time.format.DateTimeFormatter;

public class EmailSender implements TicketSender {
    private static int classId;
    @Override
    public void share(Ticket ticket){
        System.out.println("Ticket " + ticket.getTicketId() + " has been sent by Email");
    };

}
