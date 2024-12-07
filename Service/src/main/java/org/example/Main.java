package org.example;

import dao.DatabaseManager;
import dao.UserDao;
import model.ticket.Ticket;
import model.ticket.ticketTypes;
import model.users.Client;
import dao.TicketDao;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    private static final String filePath = "input.txt";
    public static void main(String[] args) {
        DatabaseManager database = new DatabaseManager();
        database.createDatabase();
        UserDao clientDao = new UserDao();
        TicketDao ticketDao = new TicketDao();

        Client newClient = new Client("John Doe", LocalDate.now());
        clientDao.saveClient(newClient);
        System.out.println("Client saved: " + newClient);
        System.out.println();

        Client fetchedClient = clientDao.getClientById(newClient.getClientId());
        System.out.println("Fetched client: " + fetchedClient);
        System.out.println();

        Ticket newTicket = new Ticket(7, newClient.getClientId(), ticketTypes.MONTH, LocalDate.now(), newClient);
        ticketDao.saveTicket(newTicket);
        System.out.println("Ticket saved: " + newTicket);
        System.out.println();


        Ticket fetchedTicket = ticketDao.getTicketById(5);
        System.out.println("Fetched ticket: " + fetchedTicket);
        System.out.println();

        List<Ticket> userTickets = ticketDao.getTicketsByUserId(newClient.getClientId());
        System.out.println("Tickets for user: " + userTickets);
        System.out.println();

        int a = newTicket.getTicketId();
        ticketDao.updateTicketType(newTicket.getTicketId(), ticketTypes.DAY);
        Ticket tick = ticketDao.getTicketById(a);
        System.out.println(tick.getTicketTicketType());
        System.out.println();

        clientDao.deleteClient(newClient.getClientId());
        System.out.println("Client and associated tickets deleted.");
        System.out.println();

        userTickets = ticketDao.getTicketsByUserId(1);
        if (userTickets.isEmpty()) {
            System.out.println("No tickets found for deleted client.");
            System.out.println();

        } else {
            System.out.println("Tickets still exist for deleted client: " + userTickets);
        }

        fetchedClient = clientDao.getClientById(newClient.getClientId());
        if (fetchedClient == null) {
            System.out.println("Client successfully deleted.");
        } else {
            System.out.println("Client still exists: " + fetchedClient);
        }
        System.out.println();


    }
}