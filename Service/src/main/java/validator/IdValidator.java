package validator;

import java.util.HashSet;
import java.util.Set;

public class IdValidator {
    private boolean uniquenessChecker(char[] toCheck, Set<String> generatedIDs){
        String idString = new String(toCheck);
        return !generatedIDs.contains(idString);
    }
    public boolean checkUniqueness(char[] toCheck, Set<String> generatedIDs){
        return uniquenessChecker(toCheck, generatedIDs);
    }
}
