package services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.ticket.Ticket;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import java.io.IOException;
import java.util.List;

public class TicketService {

    public List<Ticket> loadTicketsFromJson() throws IOException {
        Resource resource = new ClassPathResource("tickets.json");
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(resource.getInputStream(), new TypeReference<List<Ticket>>() {});
    }
}