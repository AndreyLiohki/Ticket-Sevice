package model.users;

import model.ticket.Ticket;
import java.util.Arrays;

public class Client extends User {
    private static final Roles role = Roles.CLIENT;

    //Used as a stub method
    public void getTicket(Ticket ticket){
        System.out.println(Arrays.toString(ticket.getTicketId()) + " Ticket has been got");
    }

    @Override
    public void printRole(){
        System.out.println(role);
    }

}
