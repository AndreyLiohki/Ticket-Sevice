package TIcket;

import org.example.ClassInfo;
import org.example.SetClassId;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.math.BigDecimal;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class Ticket implements SetClassId, ClassInfo {
    private static int classID;
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
    private TIcket.isPromo isPromo;
    private char stadiumSector;
    private double maxWeight;
    private BigDecimal cost;

    public Ticket(){
        this.ID = generateId();
        this.eventCode = 000;
        this.creationDate = LocalDate.now();
        this.creationTime = LocalTime.now();
        this.concertHall = "UNDEFINED";
        this.day = LocalDate.of(2000, 01,01);
        this.time = LocalTime.of(00,00,00);
        this.isPromo = TIcket.isPromo.NO;
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
        this.isPromo = TIcket.isPromo.NO;
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
            this.isPromo = TIcket.isPromo.YES;

        }else{
            this.isPromo = TIcket.isPromo.NO;

        }
        if(stadiumSector != 'A' && stadiumSector != 'B' && stadiumSector != 'C'){
            throw new IllegalArgumentException("The name of sectors must be A, B or C");
        }
        this.stadiumSector = stadiumSector;
        this.maxWeight = maxWeight;
        this.cost = cost;
    }

    public char[] GetTicketId(){
        return this.ID;
    }

    public short GetTicketEvetCode(){
        return this.eventCode;
    }

    public LocalDate GetTicketCreationDate(){
        return this.creationDate;
    }

    public LocalTime GetTicketCreationTime(){
        return this.creationTime;
    }

    public String GetTicketConcertHall(){
        return this.concertHall;
    }

    public LocalDate GetTicketDay(){
        return this.day;
    }

    public LocalTime GetTicketTime(){
        return this.time;
    }

    public isPromo GetTicketIsPromo(){
        return this.isPromo;
    }

    public char GetTicketStadiumSector(){
        return this.stadiumSector;
    }

    public double GetTicketMaxWeight(){
        return this.maxWeight;
    }

    public BigDecimal GetTicketCost(){
        return this.cost;
    }

    public void SetStadiumSector(char sector){
        this.stadiumSector = sector;
    }

    public void SetTicketDay(LocalDate date){
        this.day = date;
    }

    public void SetTicketTime(LocalTime time){
        this.time = time;
    }

    private char[] IDGenerator(){
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
            char[] idChars = IDGenerator();

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
        System.out.println("Ticket information");

        System.out.print("Ticket ID: ");
        for(int i = 0; i < this.ID.length; ++i){
            System.out.print(this.ID[i]);
        }
        System.out.println();

        System.out.println("Ticket event code: " + this.eventCode);
        System.out.println("Ticket date of creation: " + this.creationDate);
        String formattedTime = this.creationTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println("Ticket time of creation: " + formattedTime);
        System.out.println("Ticket concert hall: " + this.concertHall);
        System.out.println("Ticket day of event: " + this.day);
        System.out.println("Ticket time of event: " + this.time);
        System.out.println("Ticket promo: " + this.isPromo);
        System.out.println("Ticket stadium sector: " + this.stadiumSector);

        System.out.println("Ticket max weight: " + this.maxWeight);
        System.out.println("Ticket cost: " + this.cost);
    }

    @Override
    public void print(FileWriter file) {
        if (file == null) {
            throw new IllegalArgumentException("FileWriter cannot be null");
        }

        try {
            file.write("Ticket information\n");

            file.write("Ticket ID: ");
            for (int i = 0; i < this.ID.length; ++i) {
                file.write(this.ID[i]);
            }
            file.write("\n");

            file.write("Ticket event code: " + this.eventCode + "\n");
            file.write("Ticket date of creation: " + this.creationDate + "\n");
            String formattedTime = this.creationTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
            file.write("Ticket time of creation: " + formattedTime + "\n");
            file.write("Ticket concert hall: " + this.concertHall + "\n");
            file.write("Ticket day of event: " + this.day + "\n");
            file.write("Ticket time of event: " + this.time + "\n");
            file.write("Ticket promo: " + this.isPromo + "\n");
            file.write("Ticket stadium sector: " + this.stadiumSector + "\n");
            file.write("Ticket max weight: " + this.maxWeight + "\n");
            file.write("Ticket cost: " + this.cost + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void SetId(int id){
        classID = id;
    }

    @Override
    public int GetId(){
        return classID;
    }

    @Override
    public String toString(){
        return "Ticket{" + "ticketId = " + ID.toString() +
                ", event code = " + eventCode + ", creation date = " + creationDate +
                ", creation time = " + creationTime;
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