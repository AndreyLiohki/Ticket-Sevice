package senders;

import model.ticket.Ticket;

public interface TicketSender {
    void share(Ticket ticket);
}
