package ticket;

import org.example.Printable;
import org.example.ClassId;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public class Ticket extends ClassId implements Printable {
    private static final int ID_LENGTH = 4;
    private static final char[] CHAR_POOL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    private static Set<String> generatedIDs = new HashSet<>();

    private char[] ID;
    private short eventCode;
    private LocalDate creationDate;
    private LocalTime creationTime;

    private String concertHall;
    private LocalDate day;
    private LocalTime time;
    private PromotionAvailabilities PromotionAvailabilities;
    private char stadiumSector;
    private double maxWeight;
    private BigDecimal cost;

    public Ticket(){
        this.ID = generateId();
        this.eventCode = 000;
        this.creationDate = LocalDate.now();
        this.creationTime = LocalTime.now();
        this.concertHall = "UNDEFINED";
        this.day = LocalDate.now();
        this.time = LocalTime.now();
        this.PromotionAvailabilities = PromotionAvailabilities.NOINFO;
        this.stadiumSector = '\u0000';
        this.maxWeight = 0;
    };

    public Ticket(String concertHall, short eventCode, LocalDate day, LocalTime time){
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
        this.PromotionAvailabilities = PromotionAvailabilities.NOINFO;
        this.stadiumSector = '\u0000';
        this.maxWeight = 0;
    }

    public Ticket(short eventCode, String concertHall, LocalDate day, LocalTime time,
                  boolean isPromo, char stadiumSector, double maxWeight, BigDecimal cost){
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
            this.PromotionAvailabilities = PromotionAvailabilities.YES;

        }else{
            this.PromotionAvailabilities = PromotionAvailabilities.NO;

        }
        if(stadiumSector != 'A' && stadiumSector != 'B' && stadiumSector != 'C'){
            throw new IllegalArgumentException("The name of sectors must be A, B or C");
        }
        this.stadiumSector = stadiumSector;
        this.maxWeight = maxWeight;
        this.cost = cost;
    }

    public char[] getTicketId(){
        return this.ID;
    }

    public short getTicketEventCode(){
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

    public PromotionAvailabilities getTicketIsPromo(){
        return this.PromotionAvailabilities;
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

    public void setStadiumSector(char sector){
        this.stadiumSector = sector;
    }

    public void setTicketDay(LocalDate date){
        this.day = date;
    }

    public void setTicketTime(LocalTime time){
        this.time = time;
    }

    private char[] iDGenerator(){
        char[] generatedID = new char[ID_LENGTH];
        Random random = new Random();

        for(int i = 0; i < ID_LENGTH; ++i) {
            generatedID[i] = CHAR_POOL[random.nextInt(CHAR_POOL.length)];
        }

        return generatedID;
    }

    private boolean uniquenessChecker(char[] toCheck){
        String idString = new String(toCheck);
        return !generatedIDs.contains(idString);
    }

    private char[] generateId(){
        String id = "";
        char[] toReturn = new char[4];
        do {
            char[] idChars = iDGenerator();

            if (uniquenessChecker(idChars)) {
                id = new String(idChars);
                toReturn = idChars;
                generatedIDs.add(id);
            }
        }
        while(id == null);
        return toReturn;
    }

    @Override
    public void print(){
        System.out.println(toString());
    }
    @Override
    public void setId(int id){
        classId = id;
    }

    @Override
    public int getId(){
        return classId;
    }

    @Override
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String eventTime = time.format(formatter);
        String timeOfCreation = creationTime.format(formatter);
        return "Ticket{" + "ticketId = " + String.valueOf(ID) +
                ", event code = " + eventCode + ", creation date = " + creationDate +
                ", creation time = " + timeOfCreation + ", concert hall = " + concertHall +
                ", day = " + day + ", time = " + eventTime + ", is promo = " + PromotionAvailabilities + ", stadium sector = " + stadiumSector +
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