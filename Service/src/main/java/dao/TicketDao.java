package dao;

import model.ticket.Ticket;
import model.ticket.ticketTypes;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public class TicketDao {
    @Transactional
    public void saveTicket(Ticket ticket){
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(ticket);
        transaction.commit();
        session.close();
    }
    @Transactional(readOnly = true)
    public Ticket getTicketById(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Ticket ticket = session.get(Ticket.class, id);
        session.close();
        return ticket;
    }
    @Transactional(readOnly = true)
    public List<Ticket> getTicketsByUserId(int userId){
        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Ticket> tickets = session.createQuery("FROM Ticket t WHERE t.userId = :userId", Ticket.class)
                .setParameter("userId", userId)
                .getResultList();

        session.close();
        return tickets;
    }
    @Transactional
    public void updateTicketType(int ticketId, ticketTypes newTicketType) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();

        Ticket ticket = session.get(Ticket.class, ticketId);
        if (ticket != null) {
            ticket.setTicketType(newTicketType);
            session.update(ticket);
            transaction.commit();
        } else {
            System.out.println("Ticket with ID " + ticketId + " not found.");
        }

        session.close();
    }

}