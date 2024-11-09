package TicketService;


import org.example.ClassInfo;
import org.example.SetClassId;

import java.io.FileWriter;
import java.io.IOException;

public class TicketService implements SetClassId, ClassInfo {
    private static int classID;


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
    public void setId(int id){
        classID = id;
    }

    @Override
    public int getId(){
        return classID;
    }
}