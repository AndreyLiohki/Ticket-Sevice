package org.example;

import dao.AppConfig;
import dao.DatabaseManager;
import dao.TicketDao;
import dao.UserDao;
import model.ticket.Ticket;
import model.ticket.ticketTypes;
import model.users.Client;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        DatabaseManager databaseManager = context.getBean(DatabaseManager.class);

        Client client = new Client("Andrey", LocalDate.now());
        TicketDao daoT = context.getBean(TicketDao.class);
        UserDao daoU = context.getBean(UserDao.class);

        daoU.saveUser(client);
        System.out.println("User saved: " + client);

        Client fetchedClient = daoU.fetchUserById(client.getClientId());
        System.out.println("Fetched client: " + fetchedClient);
        System.out.println();

        Ticket newTicket = new Ticket(2, client.getClientId(), ticketTypes.MONTH, LocalDate.now(), client);
        daoT.saveTicket(newTicket);
        System.out.println("Ticket saved: " + newTicket);
        System.out.println();

        Ticket fetchedTicket = daoT.fetchTicketById(5L);
        System.out.println("Fetched ticket: " + fetchedTicket);
        System.out.println();

        List<Ticket> userTickets = daoT.fetchTicketsByClientId(client.getClientId());
        System.out.println("Tickets for user: " + userTickets);
        System.out.println();

        long ticketId = newTicket.getTicketId();
        daoT.updateTicketType(ticketId, ticketTypes.DAY);
        Ticket updatedTicket = daoT.fetchTicketById(ticketId);
        System.out.println("Updated ticket type: " + updatedTicket.getTicketTicketType());
        System.out.println();

        List<Ticket> arr = daoT.fetchTicketsByClientId(client.getClientId());
        for (int i = 0; i < arr.size(); ++i) {
            System.out.println(arr.get(i));
        }

        Client checkUpdate = daoU.fetchUserById(client.getClientId());

        daoU.deleteUser(client.getClientId());
        System.out.println("Client and associated tickets deleted.");
        System.out.println();

        userTickets = daoT.fetchTicketsByClientId(client.getClientId());
        System.out.println("Tickets after deletion: " + userTickets);

        fetchedClient = daoU.fetchUserById(client.getClientId());
        System.out.println("Client status after deletion: " + fetchedClient);
        System.out.println();

        Client testClient = new Client("Bro", LocalDate.now());
        daoU.saveUser(testClient);

        Ticket newTicket_1 = new Ticket(9, testClient.getClientId(), ticketTypes.MONTH, LocalDate.now(), testClient);
        daoU.updateUserAndCreateTicket(testClient, newTicket_1);
    }

}