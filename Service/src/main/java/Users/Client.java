package Users;

import TIcket.Ticket;

public class Client extends User {
    private static String role = "CLIENT";
    @Override
    public void PrintRole(){
        System.out.println(role);
    }

    public void getTicket(Ticket ticket){
        System.out.println("Concert hall: " + ticket.GetTicketConcertHall());
        System.out.println("Day of event: " + ticket.GetTicketDay());
        System.out.println("Time of event: " + ticket.GetTicketTime());
        System.out.println("Stadium sector: " + ticket.GetTicketStadiumSector());
        System.out.println("Max weight: " + ticket.GetTicketMaxWeight());
        System.out.println("Cost: " + ticket.GetTicketCost());
        System.out.println("Have a good day ;)");
    }
}
