package com.fourthfloor.hack.utils;


import java.util.ArrayList;

/**
 * Created by Kyle on 1/29/2016.
 */
public class Database {
    
        public static ArrayList<Employee> database = new ArrayList<Employee>();

    //finds specified employee by name
    public String find(String s){
        String k = s.toUpperCase();
        for(int i = 0; i<database.size(); i++){
            String y = database.get(i).getName();
            if(y.equals(k)) {
                return database.get(i).toString();
            }
        }
            return "Employee does not exist in database.";
    }

    //prints out the names of all employees in the database
    @Override
    public String toString(){
        String j = "";
        for(int i = 0; i<= database.size(); i++){
            j = database.get(i).getName() + "\n";
        }
    return j;
    }
}
