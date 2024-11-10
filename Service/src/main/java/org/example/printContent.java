package org.example;

public abstract class printContent implements SetClassId {
    private static int classId;

    public void print(){
        System.out.println(this.toString());
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
