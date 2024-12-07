package dao;

import model.ticket.Ticket;
import model.users.Client;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public class UserDao {
    @Transactional
    public void saveClient(Client client) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();
        session.save(client);
        transaction.commit();
        session.close();
    }
    @Transactional(readOnly = true)
    public Client getClientById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Client client = session.get(Client.class, id);
        session.close();
        return client;
    }
    @Transactional
    public void deleteClient(int clientId) {
        Transaction transaction = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        transaction = session.beginTransaction();

        Client client = session.get(Client.class, clientId);
        if (client != null) {
            List<Ticket> tickets = client.getTickets();
            for (Ticket ticket : tickets) {
                session.delete(ticket);
            }
            session.delete(client);
        }

        transaction.commit();
        session.close();
    }

}
