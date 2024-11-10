package Users;

import TIcket.Ticket;
import org.example.SetClassId;

public class Client extends User implements SetClassId {
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

    @Override
    public void setId(int id){
        classId = id;
    }

    @Override
    public int getId(){
        return classId;
    }
}
