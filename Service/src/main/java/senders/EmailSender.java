package senders;

import ticket.Ticket;
import org.example.ClassId;
import java.util.Arrays;

public class EmailSender extends ClassId implements TicketSender {
    @Override
    public void share(Ticket ticket){
        System.out.println("Ticket " + Arrays.toString(ticket.getTicketId()) + " has been sent by Email");
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
