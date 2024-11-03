package org.example;

import java.io.FileWriter;
import java.io.IOException;

public class TicketService implements SetClassId, ClassInfo {
    private static int classID;
    public static void main(String[] args) {

    }

    @Override
    public void print(){

        System.out.println("Class Id: " + classID);
    }

    @Override
    public void print(FileWriter file) {
        if (file == null) {
            throw new IllegalArgumentException("FileWriter cannot be null");
        }

        try {
            file.write("Class ID: " + classID);
        } catch (IOException e) {
            e.printStackTrace();
        }
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