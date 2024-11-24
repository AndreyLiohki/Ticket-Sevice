package model.users;

import model.ticket.Ticket;
import java.util.Arrays;

public class Admin extends User {
    private static final String role = "ADMIN";

    //Used as a stub method
    public void checkTicket(Ticket ticket){
        System.out.println(Arrays.toString(ticket.getTicketId()) + " ticket is checked");
    }

    @Override
    public void printRole(){
        System.out.println(role);
    }

}
