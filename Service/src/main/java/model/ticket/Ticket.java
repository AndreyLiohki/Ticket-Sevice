package model.ticket;

import model.users.Client;
import org.example.ClassId;
import org.example.Printable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import id.generator.IdGenerator;
import validator.IdValidator;
import javax.persistence.*;

@Entity
@Table(name = "Ticket")
public class Ticket extends ClassId implements Printable {
    private static int classID;
    private static final int ID_LENGTH = 4;
    private static final char[] CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    private static Set<String> generatedIDs = new HashSet<>();


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private Client client;

    @Enumerated(EnumType.STRING)
    @Column(name = "ticket_type", nullable = false)
    private ticketTypes ticketType;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;


    private long userId;
    private short eventCode;
    private LocalTime creationTime;
    private String concertHall;
    private LocalDate day;
    private LocalTime time;
    private PromotionAvaliabilities isPromo;
    private char stadiumSector;
    private double maxWeight;
    private BigDecimal cost;

    public Ticket(){
        this.id = 0;
        this.ticketType = ticketTypes.MONTH;
        this.eventCode = 000;
        this.creationDate = LocalDate.now();
        this.creationTime = LocalTime.now();
        this.concertHall = "UNDEFINED";
        this.day = LocalDate.of(2000, 01,01);
        this.time = LocalTime.of(00,00,00);
        this.isPromo = PromotionAvaliabilities.NOINFO;
        this.stadiumSector = '\u0000';
        this.maxWeight = 0;
        this.cost = null;
        this.userId = 1;
    };
    public Ticket(long id, long userId, ticketTypes ticketType, LocalDate creationDate, Client client){

        this.id = id;
        this.eventCode = 2;
        this.client = client;
        this.creationDate = creationDate;
        this.creationTime = LocalTime.now();
        this.userId = userId;
        this.day = LocalDate.now();
        this.time = LocalTime.now();
        this.isPromo = PromotionAvaliabilities.NOINFO;
        this.stadiumSector = 'C';
        this.maxWeight = 0;
        this.ticketType = ticketType;
    }
    public Ticket(LocalDate creationDate, LocalTime creationTime, ticketTypes ticketType, BigDecimal cost) {

        this.id = id;
        this.eventCode = 2;
        this.client = client;
        this.creationDate = creationDate;
        this.creationTime = creationTime;
        this.userId = userId;
        this.day = LocalDate.now();
        this.time = LocalTime.now();
        this.isPromo = PromotionAvaliabilities.NOINFO;
        this.stadiumSector = '\u0000';
        this.maxWeight = 0;
        this.ticketType = ticketType;
        this.cost = cost;
    }
    public char[] generateId(){
        String id = null;
        char[] toReturn = new char[4];
        IdGenerator generator = new IdGenerator();
        IdValidator validator = new IdValidator();
        do {
            char[] idChars = generator.generate(ID_LENGTH, CHAR_POOL);

            if (validator.checkUniqueness(idChars, generatedIDs)) {
                id = new String(idChars);
                toReturn = idChars;
                generatedIDs.add(id);
                break;
            }
        }
        while(id == null);
        return toReturn;
    }
    public long getTicketId(){
        return this.id;
    }
    public short getTicketEvetCode(){
        return this.eventCode;
    }
    public LocalDate getTicketCreationDate(){
        return this.creationDate;
    }
    public LocalTime getTicketCreationTime(){
        return this.creationTime;
    }
    public String getTicketConcertHall(){
        return this.concertHall;
    }
    public LocalDate getTicketDay(){
        return this.day;
    }
    public LocalTime getTicketTime(){
        return this.time;
    }
    public PromotionAvaliabilities getTicketIsPromo(){
        return this.isPromo;
    }
    public char getTicketStadiumSector(){
        return this.stadiumSector;
    }
    public double getTicketMaxWeight(){
        return this.maxWeight;
    }
    public BigDecimal getTicketCost(){
        return this.cost;
    }
    public long getTicketUserId() { return userId; }
    public ticketTypes getTicketTicketType() { return ticketType; }
    public void setStadiumSector(char sector){
        this.stadiumSector = sector;
    }
    public void setTicketDay(LocalDate date){
        this.day = date;
    }
    public void setTicketTime(LocalTime time){
        this.time = time;
    }
    public void setTicketTicketType(ticketTypes newType) {
        this.ticketType = newType;
    }
    public void setTicketType(ticketTypes newTicketType) {
        this.ticketType = newTicketType;
    }

    @Override
    public void print(){
        System.out.println(this.toString());
    }

    @Override
    public void setId(int id){
        classID = id;
    }

    @Override
    public int getId(){
        return classID;
    }

    @Override
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String eventTime = time.format(formatter);
        String timeOfCreation = creationTime.format(formatter);
        return "Ticket{" + "ticketId = " + id +
                ", event code = " + eventCode + ", creation date = " + creationDate +
                ", creation time = " + timeOfCreation + ", concert hall = " + concertHall +
                ", day = " + day + ", time = " + eventTime + ", is promo = " + isPromo + ", stadium sector = " + stadiumSector +
                ", max weight = " + maxWeight + ", cost = " + cost + "}";
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        return true;
    }

    @Override
    public int hashCode(){
        return this.hashCode();
    }


}