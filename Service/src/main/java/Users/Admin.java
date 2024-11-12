package Users;

import TIcket.Ticket;
import org.example.SetClassId;

public class Admin extends User {
    private static int classId;
    private static String role = "ADMIN";

    //Used as a stub method
    public void checkTicket(Ticket ticket){
        System.out.println(ticket.getTicketId() + " ticket is checked");
    }

    @Override
    public void PrintRole(){
        System.out.println(role);
    }
}
