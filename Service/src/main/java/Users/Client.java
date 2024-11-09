package Users;

import TIcket.Ticket;

public class Client extends User {
    private static String role = "CLIENT";
    @Override
    public void PrintRole(){
        System.out.println(role);
    }

}
