package dao;

import model.ticket.Ticket;
import model.ticket.ticketTypes;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class TicketDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public TicketDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void saveTicket(Ticket ticket) {
        Session session = sessionFactory.getCurrentSession();
        session.save(ticket);
    }

    public List<Ticket> fetchTicketsByClientId(Long clientId) {
        String hql = "FROM Ticket t WHERE t.client.id = :clientId";
        Query<Ticket> query = sessionFactory.getCurrentSession().createQuery(hql, Ticket.class);
        query.setParameter("clientId", clientId);
        return query.list();
    }

    public Ticket fetchTicketById(long id) {
        return sessionFactory.getCurrentSession().get(Ticket.class, id);
    }

    public void updateTicketType(Long id, ticketTypes newType) {
        Ticket ticket = sessionFactory.getCurrentSession().get(Ticket.class, id);
        if (ticket != null) {
            ticket.setTicketTicketType(newType);
            sessionFactory.getCurrentSession().update(ticket);
        }
    }

    public void deleteTicket(Long id) {
        Ticket ticket = sessionFactory.getCurrentSession().get(Ticket.class, id);
        if (ticket != null) {
            sessionFactory.getCurrentSession().delete(ticket);
        }
    }
}