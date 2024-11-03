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


}