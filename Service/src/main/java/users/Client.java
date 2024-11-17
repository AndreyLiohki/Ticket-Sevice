package users;

import ticket.Ticket;
import java.util.Arrays;

public class Client extends User {
    private static final Roles role = Roles.CLIENT;
    private final Ticket ticket = new Ticket();

    //Used as a stub method
    public Ticket getTicket(){
        System.out.println(Arrays.toString(ticket.getTicketId()) + " Ticket has been got");
        return ticket;
    }

    @Override
    public void printRole(){
        System.out.println(role);
    }

}
