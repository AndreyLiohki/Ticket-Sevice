package id.generator;

import java.util.Random;

public class IdGenerator {
    private char[] iDGenerator(int ID_LENGTH, final char[] CHAR_POOL){
        char[] generatedID = new char[ID_LENGTH];
        Random random = new Random();

        for(int i = 0; i < ID_LENGTH; ++i) {
            generatedID[i] = CHAR_POOL[random.nextInt(CHAR_POOL.length)];
        }

        return generatedID;
    }
    public char[] generate(int ID_LENGTH, final char[] CHAR_POOL){
        return  iDGenerator(ID_LENGTH, CHAR_POOL);
    }
}
