package validator;

import ticket.ticketTypes;
import org.example.ClassId;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class Validator extends ClassId {

    private static Set<String> validValues = new HashSet<>();

    public Validator(){
        for(ticketTypes type: ticketTypes.values()){
            validValues.add(type.name());
        }
    }

    public boolean isValidTicket(LocalDate date, String typeOfTicket, BigDecimal cost){
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
