package Users;

import TIcket.Ticket;

public class Client extends User {
    private static int classId;
    private static String role = "CLIENT";

    //Used as a stub method
    public Ticket getTicket(){
        Ticket ticket = new Ticket();
        return ticket;
    }

    @Override
    public void PrintRole(){
        System.out.println(role);
    }

}
