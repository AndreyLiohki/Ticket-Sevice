package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.math.BigDecimal;

public class Ticket implements SetClassId{

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
    private boolean isPromo;
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
        this.isPromo = false;
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
        this.isPromo = false;
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
        this.isPromo = isPromo;
        if(stadiumSector != 'A' && stadiumSector != 'B' && stadiumSector != 'C'){
            throw new IllegalArgumentException("The name of sectors must be A, B or C");
        }
        this.stadiumSector = stadiumSector;
        this.maxWeight = maxWeight;
        this.cost = cost;
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
    public void SetId(int id){
        classID = id;
    }

    @Override
    public int GetId(){
        return classID;
    }
}