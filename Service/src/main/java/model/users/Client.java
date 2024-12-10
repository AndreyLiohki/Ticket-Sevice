package model.users;

import model.ticket.Ticket;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "Client")
public class Client extends User {
    private static final Roles role = Roles.CLIENT;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long clientId;

    @Column(name = "Name", nullable = false, length = 100)
    private String name;

    @Column(name = "Creation_date", nullable = false)
    private LocalDate creationDate;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Ticket> tickets;

    private Activation status;
    public Client(){}
    public Client(String name, LocalDate creationDate) {
        this.name = name;
        this.creationDate = creationDate;
    }
    public Client(String name, int id, LocalDate creationDate) {
        this.name = name;
        this.clientId = id;
        this.creationDate = creationDate;
    }
    public String getName() {
        return name;
    }
    public long getClientId() {
        return clientId;
    }
    public LocalDate getCreationDate() {
        return creationDate;
    }
    public List<Ticket> getTickets() {
        return tickets;
    }
    public void addTicket(Ticket ticket) {
        System.out.println(ticket.getTicketId() + " Ticket has been received");
    }
    public void setActivation(Activation isActivated){this.status = isActivated;}
    public Activation getActivation(){return status;}



    @Override
    public void printRole() {
        System.out.println(role);
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + clientId +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }


}