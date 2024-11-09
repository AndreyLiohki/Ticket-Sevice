package Users;

import TIcket.Ticket;

public class Client extends User {
    private static int classId;
    private static String role = "CLIENT";

    //Used as a stub method
    public void getTicket(Ticket ticket){
        System.out.println(ticket.getTicketId() + " Ticket has been got");
    }

    @Override
    public void PrintRole(){
        System.out.println(role);
    }

}
