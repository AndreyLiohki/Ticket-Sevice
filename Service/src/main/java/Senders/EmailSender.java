package Senders;

import TIcket.Ticket;
import org.example.SetClassId;

import java.time.format.DateTimeFormatter;
import java.util.Set;

public class EmailSender extends SetClassId implements TicketSender {
    private static int classID;

    @Override
    public void share(Ticket ticket){
        System.out.println("Ticket " + ticket.getTicketId() + " has been sent by Email");
    };

    @Override
    public void setId(int id){
        classID = id;
    }

    @Override
    public int getId(){
        return classID;
    }
}
