package Reader;

import org.example.SetClassId;
import java.io.BufferedReader;
public class Reader implements SetClassId {
    private static int classId;


    @Override
    public void setId(int id){
        classId = id;
    }

    @Override
    public int getId(){
        return classId;
    }
}
