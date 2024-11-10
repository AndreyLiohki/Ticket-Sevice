package Senders;

import TIcket.Ticket;
import org.example.SetClassId;

import java.time.format.DateTimeFormatter;

public class EmailSender implements TicketSender, SetClassId {
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
