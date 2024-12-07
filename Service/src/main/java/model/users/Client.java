package model.users;

import model.ticket.Ticket;
import java.time.LocalDate;
import java.util.Arrays;

public class Client extends User {
    private static final Roles role = Roles.CLIENT;
    private String name;
    private int clientId;
    private LocalDate creationDate;

    public Client(String name, int clientId, LocalDate creationDate) {
        this.name = name;
        this.clientId = clientId;
        this.creationDate = creationDate;
    }

    public String getName() {
        return name;
    }

    public int getClientId() {
        return clientId;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void getTicket(Ticket ticket){
        System.out.println((ticket.getTicketId()) + " Ticket has been got");
    }

    @Override
    public void printRole(){
        System.out.println(role);
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}
