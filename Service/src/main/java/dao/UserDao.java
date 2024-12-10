package dao;

import model.ticket.Ticket;
import model.users.Client;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDao(HibernateUtil hibernateUtil) {
        this.sessionFactory = hibernateUtil.getSessionFactory();
    }

    public void saveUser(Client client) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.save(client);
        transaction.commit();
        session.close();
    }

    public Client fetchUserById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Client.class, id);
        }
    }

    public void deleteUser(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Client user = session.get(Client.class, id);
            if (user != null) {
                List<Ticket> tickets = user.getTickets();
                for (Ticket ticket : tickets) {
                    Ticket ticketToDelete = session.get(Ticket.class, ticket.getTicketId());
                    if (ticketToDelete != null) {
                        session.delete(ticketToDelete);
                    } else {
                        System.out.println("Ticket with ID " + ticket.getTicketId() + " not found, cannot delete.");
                    }
                }
                session.delete(user);
            } else {
                System.out.println("Client with ID " + id + " not found.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Error deleting user: " + e.getMessage());
        } finally {
            session.close();
        }
    }
}