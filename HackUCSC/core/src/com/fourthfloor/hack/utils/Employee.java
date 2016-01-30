package com.fourthfloor.hack.utils;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;
import java.util.Date;

public class Employee {

    public ArrayList<String> employees;
    public ArrayList<String> hours;

    public double salary;
    public String name;
    public String benefits;
    public String race;
    public String gender;
    public int sickDays;
    public int vD;
    public Sprite pic;
    //total hours
    public double hr;

    public Employee(){

        salary = 0;
        name = "";
        hours = new ArrayList<String>();
        benefits = "";
        race = "";
        gender = "";
        sickDays = 0;
        vD = 0;
        hr = 0;
    }

    //salary,name,benefits,race,gender,sick days,vacation days
    public Employee(double x, String y, String k, String t, String e, int g, int u){
        salary = x;
        name = y.toUpperCase();
        benefits = k;
        race = t;
        gender = e;
        sickDays = g;
        vD = u;
        hours = new ArrayList<String>();
        hr = 0;
    }

    public void clock(){
        Date date = new Date(TimeUtils.millis());
        hours.add(0,date.toString().split(" ")[3]);
    }

    public String getBenefits(){
        return benefits;
    }

    public String getRace(){
        return race;
    }

    public String getGender(){
        return gender;
    }

    public double getSalary(){
        return salary;
    }

    public int getSickDays(){
        return sickDays;
    }

    public int getVD(){
        return vD;
    }

    public String getClock(){
        String m = "";
            m = hours.get(7) +" - "+
                    hours.get(6) +"\n" +
                    hours.get(5) + " - "+
                    hours.get(4) +"\n" +
                    hours.get(3) +" - "+
                    hours.get(2) +"\n"+
                    hours.get(1) +" - "+
                    hours.get(0);
        return m;
    }

    public double getHours(){
        return hr;
    }
    //TODO NEEDS TO BE CALLED AFTER LAST CLOCK FOR EACH EMPLOYEE
    public void signOut(){
        double a = Double.parseDouble(hours.get(7));
        double b = Double.parseDouble(hours.get(6));
        double c = Double.parseDouble(hours.get(5));
        double d = Double.parseDouble(hours.get(4));
        double e = Double.parseDouble(hours.get(3));
        double f = Double.parseDouble(hours.get(2));
        double g = Double.parseDouble(hours.get(1));
        double h = Double.parseDouble(hours.get(0));
        double t = a-b+c-d+e-f+g-h;
        hr+=t;
    }

    public Sprite getPic(){
        return pic;
    }
}

