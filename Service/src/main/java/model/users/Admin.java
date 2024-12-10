package model.users;

import model.ticket.Ticket;

public class Admin extends User {
    private static final String role = "ADMIN";

    public void checkTicket(Ticket ticket){
        System.out.println(ticket.getTicketId() + " ticket is checked");
    }

    @Override
    public void printRole(){
        System.out.println(role);
    }

}
