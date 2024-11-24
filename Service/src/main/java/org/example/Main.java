package org.example;

import dao.UserDAO;
import model.ticket.Ticket;
import model.ticket.ticketTypes;
import model.users.Client;
import reader.FileReader;
import dao.TicketDao;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Main {
    private static final String filePath = "input.txt";
    public static void main(String[] args) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.print();

        Client client = new Client("Andrey", 1, LocalDate.now());
        FileReader reader = new FileReader();

        List<Ticket> tickets  = reader.readTicketsFromFile(filePath);
        for(Ticket item:tickets){
            item.print();
        }

        UserDAO userDAO = new UserDAO();
        userDAO.saveUser(client);
        System.out.println("Ticket saved successfully.");

        TicketDao ticketDao = new TicketDao();
        ticketDao.saveTicket(ticket);
        System.out.println("Ticket saved successfully.");

        tickets = ticketDao.fetchTicketsByUserId(1);
        for(Ticket item:tickets){
            item.print();
        }

        ticketTypes type = ticketTypes.DAY;
        ticketDao.updateTicketType(ticket.getTicketId(), type);

        tickets = ticketDao.fetchTicketsByUserId(1);
        for(Ticket item:tickets){
            item.print();
        }
        System.out.println();
        System.out.println();
        char[] id = {'M', 'F', 'O', 'C'};
        Ticket t1 = ticketDao.fetchTicketById(id);
        t1.print();
    }
}
