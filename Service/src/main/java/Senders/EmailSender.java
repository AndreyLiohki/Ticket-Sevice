package Senders;

import TIcket.Ticket;
import org.example.SetClassId;

import java.time.format.DateTimeFormatter;

public class EmailSender extends SetClassId implements TicketSender {
    private static int classId;
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
