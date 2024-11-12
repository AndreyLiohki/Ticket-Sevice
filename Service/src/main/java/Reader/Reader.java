package Reader;

import org.example.SetClassId;

public class Reader extends SetClassId {
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
