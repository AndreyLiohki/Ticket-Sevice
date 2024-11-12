package Senders;

import TIcket.Ticket;
import org.example.SetClassId;

import java.time.format.DateTimeFormatter;

public class TelSender extends SetClassId implements TicketSender{
    private static int classId;
    @Override
    public void share(Ticket ticket){
        System.out.println("Ticket " + ticket.getTicketId() + " has been sent by telephone");
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
