package dao;

import model.ticket.Ticket;
import model.users.Activation;
import model.users.Client;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class UserDao {
    private final SessionFactory sessionFactory;

    @Value("${app.updateUserAndCreateTicket.enabled}")
    private boolean updateUserAndCreateTicketEnabled;

    @Autowired
    public UserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveUser(Client client) {
        sessionFactory.getCurrentSession().save(client);
    }

    public Client fetchUserById(long id) {
        return sessionFactory.getCurrentSession().get(Client.class, id);
    }

    public void deleteUser(long id) {
        Client user = sessionFactory.getCurrentSession().get(Client.class, id);
        if (user != null) {
            List<Ticket> tickets = user.getTickets();
            for (Ticket ticket : tickets) {
                Ticket ticketToDelete = sessionFactory.getCurrentSession().get(Ticket.class, ticket.getTicketId());
                if (ticketToDelete != null) {
                    sessionFactory.getCurrentSession().delete(ticketToDelete);
                } else {
                    System.out.println("Ticket with ID " + ticket.getTicketId() + " not found, cannot delete.");
                }
            }
            sessionFactory.getCurrentSession().delete(user);
        } else {
            System.out.println("Client with ID " + id + " not found.");
        }
    }
    public void updateUserAndCreateTicket(Client client, Ticket newTicket) {
        if (!updateUserAndCreateTicketEnabled) {
            throw new IllegalStateException("Updating user and creating ticket is disabled.");
        }
        client.setActivation(Activation.ACTIVATED);
        client.addTicket(newTicket);
        sessionFactory.getCurrentSession().update(client);
        TicketDao ticketDao = new TicketDao(sessionFactory);
        ticketDao.saveTicket(newTicket);
    }
}