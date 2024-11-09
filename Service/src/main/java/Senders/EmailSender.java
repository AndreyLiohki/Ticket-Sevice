package Senders;

import TIcket.Ticket;

import java.time.format.DateTimeFormatter;

public class EmailSender extends Sender {
    @Override
    public void shared(Ticket ticket){
        String formattedTime = ticket.GetTicketCreationTime().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println("Ticket ID " + ticket.GetTicketId()[0] +  ticket.GetTicketId()[1] + ticket.GetTicketId()[2] + ticket.GetTicketId()[3]+ " have been shared by email");
        System.out.println("Ticket event code " + ticket.GetTicketEvetCode() + " have been shared by email");
        System.out.println("Ticket creation date " + ticket.GetTicketCreationDate() + " have been shared by email");
        System.out.println("Ticket creation tome " + ticket.GetTicketCreationTime() + " have been shared by email");
        System.out.println("Ticket concert hall " + ticket.GetTicketConcertHall() + " have been shared by email");
        System.out.println("Ticket day of event " + ticket.GetTicketDay() + " have been shared by email");
        System.out.println("Ticket time of event " + ticket.GetTicketTime() + " have been shared by email");
        System.out.println("Ticket promo " + ticket.GetTicketIsPromo() + " have been shared by email");
        System.out.println("Ticket stadium sector " + ticket.GetTicketStadiumSector() + " have been shared by email");
        System.out.println("Ticket max weight " + ticket.GetTicketMaxWeight() + " have been shared by email");
        System.out.println("Ticket cost " + ticket.GetTicketCost() + " have been shared by email");
    };

}
