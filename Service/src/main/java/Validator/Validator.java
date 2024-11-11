package Validator;

import TIcket.ticketTypes;
import org.example.SetClassId;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

public class Validator implements SetClassId {
    private static int classId;

    private static Set<String> validValues = new HashSet<>();
    static{
        for(ticketTypes type: ticketTypes.values()){
            validValues.add(type.name());
        }
    }

    public static boolean isValidTicket(LocalDate date, String typeOfTicket, BigDecimal cost){
        if(date.isAfter(LocalDate.now())){
            return false;
        }

        if(!validValues.contains(typeOfTicket)){
            return false;

        }
        BigDecimal divisor = new BigDecimal(2);
        BigDecimal zero = new BigDecimal(0);
        if(!cost.remainder(divisor).equals(zero)){
            return false;
        }

        return true;
    }

    @Override
    public void setId(int id){
        classId = id;
    }

    @Override
    public int getId(){
        return classId;
    }
}
