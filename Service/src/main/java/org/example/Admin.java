package org.example;

public class Admin extends User{
    private static String role = "ADMIN";

    @Override
    public void PrintRole(){
        System.out.println(role);
    }
    public void checkTicket(Ticket ticket){
        System.out.print(ticket.GetTicketId() + "has been checked");
    }
}
