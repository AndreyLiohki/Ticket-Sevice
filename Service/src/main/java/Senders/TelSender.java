package Senders;

import TIcket.Ticket;

import java.time.format.DateTimeFormatter;

public class TelSender implements TicketSender {
    private static int classId;
    @Override
    public void share(Ticket ticket){
        System.out.println("Ticket " + ticket.getTicketId() + " has been sent by telephone");
    };
}
