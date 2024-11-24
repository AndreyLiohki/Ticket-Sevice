package senders;

import model.ticket.Ticket;
import org.example.ClassId;

public class EmailSender extends ClassId implements TicketSender {

    @Override
    public void share(Ticket ticket){
        System.out.println("Ticket " + ticket.getTicketId() + " has been sent by Email");
    };

    @Override
    public void setId(int id){
        classId = id;
    }

    @Override
    public int getId(){
        return classId;
    }
}
