package dao;

import model.ticket.Ticket;
import model.ticket.ticketTypes;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TicketDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public TicketDao(HibernateUtil hibernateUtil) {
        this.sessionFactory = hibernateUtil.getSessionFactory();
    }

    public void saveTicket(Ticket ticket) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        session.save(ticket);
        transaction.commit();
        session.close();
    }
    public List<Ticket> fetchTicketsByClientId(Long clientId) {
        List<Ticket> tickets = null;
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Ticket t WHERE t.client.id = :clientId";
            Query<Ticket> query = session.createQuery(hql, Ticket.class);
            query.setParameter("clientId", clientId);
            tickets = query.list();
        }
        return tickets;
    }

    public Ticket fetchTicketById(long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Ticket.class, id);
        }
    }

    public void updateTicketType(Long id, ticketTypes newType) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Ticket ticket = session.get(Ticket.class, id);
        if (ticket != null) {
            ticket.setTicketTicketType(newType);
            session.update(ticket);
        }
        transaction.commit();
        session.close();
    }

    public void deleteTicket(Long id) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Ticket ticket = session.get(Ticket.class, id);
        if (ticket != null) {
            session.delete(ticket);
        }
        transaction.commit();
        session.close();
    }
}