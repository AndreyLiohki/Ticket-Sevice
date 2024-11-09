package Senders;

import TIcket.Ticket;

import java.time.format.DateTimeFormatter;

public class EmailSender extends Sender {
    private static int classId;
    @Override
    public void shared(Ticket ticket){
        System.out.println("Ticket " + ticket.getTicketId() + " has been sent by Email");
    };

}
