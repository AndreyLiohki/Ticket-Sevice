package model.ticket;

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

public class Ticket extends ClassId implements Printable {
    private static int classID;
    private static final int ID_LENGTH = 4;
    private static final char[] CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    private static Set<String> generatedIDs = new HashSet<>();

    private char[] ID;
    private short eventCode;
    private LocalDate creationDate;
    private LocalTime creationTime;

    private ticketTypes ticketType;
    private String concertHall;
    private LocalDate day;
    private LocalTime time;
    private PromotionAvaliabilities isPromo;
    private char stadiumSector;
    private double maxWeight;
    private BigDecimal cost;
    private int userId;

    public Ticket(){
        IdGenerator generator = new IdGenerator();
        this.ID = generateId();
        this.eventCode = 000;
        this.creationDate = LocalDate.now();
        this.creationTime = LocalTime.now();
        this.concertHall = "UNDEFINED";
        this.day = LocalDate.of(2000, 01,01);
        this.time = LocalTime.of(00,00,00);
        this.isPromo = PromotionAvaliabilities.NOINFO;
        this.stadiumSector = '\u0000';
        this.maxWeight = 0;
        this.ticketType = ticketTypes.MONTH;
        this.userId = 1;
    };

    public Ticket(String concertHall, short eventCode, LocalDate day, LocalTime time){
        IdGenerator generator = new IdGenerator();
        this.ID = generateId();
        if(eventCode<100 || eventCode>999){
            throw new IllegalArgumentException("Enter 3-digit code");
        }
        this.eventCode = eventCode;
        this.creationDate = LocalDate.now();
        this.creationTime = LocalTime.now();
        if(concertHall.length()>10){
            throw new IllegalArgumentException("The name of the concert hall should not exceed 10 characters");
        }
        this.concertHall = concertHall;
        this.day = day;
        this.time = time;
        this.isPromo = PromotionAvaliabilities.NOINFO;
        this.stadiumSector = '\u0000';
        this.maxWeight = 0;
        this.ticketType = ticketTypes.NOINFO;
        this.userId = 0;
    }

    public Ticket(char[] id, int userId, ticketTypes ticketType, LocalDate creationDate){
        this.ID = id;
        this.eventCode = 0;
        this.creationDate = creationDate;
        this.creationTime = LocalTime.now();
        this.userId = userId;
        this.day = LocalDate.now();
        this.time = LocalTime.now();
        this.isPromo = PromotionAvaliabilities.NOINFO;
        this.stadiumSector = '\u0000';
        this.maxWeight = 0;
        this.ticketType = ticketType;
    }

    public Ticket(LocalDate creationDate, LocalTime creationTime, ticketTypes ticketType, BigDecimal cost){
        IdGenerator generator = new IdGenerator();
        this.ID = generateId();
        this.eventCode = 0;
        this.creationDate = creationDate;
        this.creationTime = creationTime;

        this.day = LocalDate.now();
        this.time = LocalTime.now();
        this.isPromo = PromotionAvaliabilities.NOINFO;
        this.stadiumSector = '\u0000';
        this.maxWeight = 0;
        this.cost = cost;
        this.ticketType = ticketType;
    }

    public Ticket(short eventCode, String concertHall, LocalDate day, LocalTime time,
                  boolean isPromo, char stadiumSector, double maxWeight, BigDecimal cost, ticketTypes ticketType, int userId){
        IdGenerator generator = new IdGenerator();
        this.ID = generateId();
        if(eventCode<100 || eventCode>999) {
            throw new IllegalArgumentException("Enter 3-digit code");
        }
        this.eventCode = eventCode;
        this.creationDate = LocalDate.now();
        this.creationTime = LocalTime.now();
        if(concertHall.length()>10){
            throw new IllegalArgumentException("The name of the concert hall should not exceed 10 characters");
        }
        this.concertHall = concertHall;
        this.day = day;
        this.time = time;
        if(isPromo){
            this.isPromo = PromotionAvaliabilities.YES;

        }else{
            this.isPromo = PromotionAvaliabilities.NO;

        }
        if(stadiumSector != 'A' && stadiumSector != 'B' && stadiumSector != 'C'){
            throw new IllegalArgumentException("The name of sectors must be A, B or C");
        }
        this.stadiumSector = stadiumSector;
        this.maxWeight = maxWeight;
        this.cost = cost;
        this.ticketType = ticketType;
        this.userId = userId;
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

    public char[] getTicketId(){
        return this.ID;
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

    public int getTicketUserId() { return userId; }

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
        return "Ticket{" + "ticketId = " + String.valueOf(ID) +
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