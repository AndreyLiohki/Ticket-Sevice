package senders;

import model.ticket.Ticket;
import org.example.ClassId;

public class TelephoneSender extends ClassId implements TicketSender {
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
