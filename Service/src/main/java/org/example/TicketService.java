package org.example;

public class TicketService implements SetClassId {
    private static int classID;
    public static void main(String[] args) {

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