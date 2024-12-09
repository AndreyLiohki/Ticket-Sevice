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

        // Сохранение пользователя
        daoU.saveUser(client);
        System.out.println("User saved: " + client);

        // Получение пользователя
        Client fetchedClient = daoU.fetchUserById(client.getClientId());
        System.out.println("Fetched client: " + fetchedClient);
        System.out.println();

        // Сохранение билета
        Ticket newTicket = new Ticket(2, client.getClientId(), ticketTypes.MONTH, LocalDate.now(), client);
        daoT.saveTicket(newTicket);
        System.out.println("Ticket saved: " + newTicket);
        System.out.println();

        // Получение билета по ID
        Ticket fetchedTicket = daoT.fetchTicketById(5L);
        System.out.println("Fetched ticket: " + fetchedTicket);
        System.out.println();

        // Получение всех билетов клиента
        List<Ticket> userTickets = daoT.fetchTicketsByClientId(client.getClientId());
        System.out.println("Tickets for user: " + userTickets);
        System.out.println();

        // Изменение типа билета
        long ticketId = newTicket.getTicketId();
        daoT.updateTicketType(ticketId, ticketTypes.DAY);
        Ticket updatedTicket = daoT.fetchTicketById(ticketId);
        System.out.println("Updated ticket type: " + updatedTicket.getTicketTicketType());
        System.out.println();

        List<Ticket> arr = daoT.fetchTicketsByClientId(client.getClientId());
        for(int i = 0; i < arr.size(); ++i){
            System.out.println(arr.get(i));
        }
        daoU.deleteUser(client.getClientId());
        System.out.println("Client and associated tickets deleted.");
        System.out.println();

        // Проверка билетов после удаления клиента
        userTickets = daoT.fetchTicketsByClientId(client.getClientId());
        System.out.println("Tickets after deletion: " + userTickets);

        // Проверка существования клиента после удаления
        fetchedClient = daoU.fetchUserById(client.getClientId());
        System.out.println("Client status after deletion: " + fetchedClient);
        System.out.println();
    }
}