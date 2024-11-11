package Reader;

import Validator.Validator;
import org.example.SetClassId;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import TIcket.ticketTypes;
import TIcket.Ticket;
import java.time.LocalDate;

public class Reader implements SetClassId {
    private static int classId;

    public static List<Ticket> readTicketsFromFile(String filePath) {
        List<Ticket> tickets = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
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
                    System.out.println("Ticket was successfully read");
                }else{
                    System.out.println("Error. Invalid ticket data");
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tickets;
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
