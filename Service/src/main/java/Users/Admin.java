package Users;

import TIcket.Ticket;
import org.example.SetClassId;

public class Admin extends User implements SetClassId {
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

    @Override
    public void setId(int id){
        classId = id;
    }

    @Override
    public int getId(){
        return classId;
    }
}
