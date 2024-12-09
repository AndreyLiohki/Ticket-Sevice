package reader;

import validator.Validator;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import model.ticket.ticketTypes;
import model.ticket.Ticket;
import java.time.LocalDate;

public class FileReader {
    public List<Ticket> readTicketsFromFile(String filePath) {
        List<Ticket> tickets = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new java.io.FileReader(filePath))) {
            String line;
            int i = 1;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                LocalDate creationDate = LocalDate.parse(parts[0]);
                LocalTime creationTime = LocalTime.parse(parts[1]);
                String typeOfTicket = parts[2].toUpperCase();
                BigDecimal cost = new BigDecimal(parts[3]);
                Validator validator = new Validator();
                if(validator.isValidTicket(creationDate, typeOfTicket, cost)){
                    ticketTypes ticketType = ticketTypes.valueOf(parts[2].toUpperCase());
                    tickets.add(new Ticket(creationDate, creationTime, ticketType, cost));
                    System.out.println("Ticket " + i + " was successfully read");
                }else{
                    System.out.println("Error. Invalid ticket data. Ticket " + i + " cannot be read");
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tickets;
    }
}
