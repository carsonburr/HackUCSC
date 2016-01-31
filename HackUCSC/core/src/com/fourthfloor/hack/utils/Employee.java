package com.fourthfloor.hack.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.ArrayList;
import java.util.Date;

public class Employee {

    public ArrayList<String> hours;

    private double salary;
    private String name;
    private String benefits;
    //vacation days
    public static int vD = 0;
    private Sprite pic;
    //total hours
    private double hr;
    //sick day hours
    private double shr;
    //can be polished** @DJWhiteMocha
    //max sick day hours
    public static double shrMax = 900;
    //normal work hours
    public static double nwd;
    //TODO hours until overtime  **change
    public static double ovt = 41;
    //hours worked
    private double hw;
    //hours worked that day
    public double hwd;
    //arraylist to determine popup
    public ArrayList<String> popup = new ArrayList<String>();
    //static variable to determine if there are hours
    public static boolean exist = false;

    //default constructor
    public Employee(){

        salary = 0;
        name = "";
        hours = new ArrayList<String>();
        benefits = "";
        vD = 0;
        hr = 0;
        shr = 0;
        hw = 0;
        hwd = 0;
    }

    //salary,name,benefits, picture
    public Employee(double x, String y, String k, String p){
        salary = x;
        name = y.toUpperCase();
        benefits = k;
        hours = new ArrayList<String>();
        hr = 0;
        shr = 0;
        hw = 0;
        pic = new Sprite(new Texture(p));
    }
    //boolean return for if the first cell in the array hours holds a value
    public void HE(){
        if(hours.get(0)!= null){
            exist = true;
        }
    }
    //get total hours worked
    public double getHours(){
        double result = 0;
        for(int i = 0; i<hours.size()/2; i++){
            double e = Double.parseDouble(hours.get(i*2));
            double k = Double.parseDouble(hours.get(i*2 +1));
            result += k-e;
        }
        return result;
    }
    //sets the normal work week hours
    public void setNWWH(double r){
        nwd = r;
    }
    //sets the maximum number of sick day hours
    public void setSDH(double f){
        shrMax = f;
    }
    //sets the salary of the employee
    public void setSalary(double e){
        salary = e;
    }
    //adds a popup message to the arraylist popups
    public void addPopup(String e){
        popup.add(e);
    }
    //get hours worked that day
    public double getHWD(){
        double a = Double.parseDouble(hours.get(hours.size()-8));
        double b = Double.parseDouble(hours.get(hours.size()-7));
        double c = Double.parseDouble(hours.get(hours.size()-6));
        double d = Double.parseDouble(hours.get(hours.size()-5));
        double e = Double.parseDouble(hours.get(hours.size()-4));
        double f = Double.parseDouble(hours.get(hours.size()-3));
        double g = Double.parseDouble(hours.get(hours.size()-2));
        double h = Double.parseDouble(hours.get(hours.size()-1));
        hwd = a-b+c-d+e-f+g-h;
        return hwd;
    }
    //returns name of employee
    public String getName(){
        return name;
    }
    //gets hours of overtime
    public double getOVT(){
        return ovt;
    }
    //calculate overtime hours
    public double calcOVT(){
        ovt = 41 - hw;
        if(ovt<0){
            ovt = 0;
        }
        return ovt;
    }
    //adds the clock in and clock out times for the employee
    public void clock(){
        Date date = new Date(TimeUtils.millis());
        hours.add(hours.size(),date.toString().split(" ")[3]);
        if(hours.size() %8 == 0){
            signOut();
        }
    }
    //gets the sick hours of the employee
    public double getSickHours(){
        return shr;
    }
    //gets the benefits of the employee
    public String getBenefits(){
        return benefits;
    }
    //gets the salary of employee
    public double getSalary(){
        return salary;
    }
    //gets the vacation days for the employee
    public int getVD(){
        return vD;
    }
    //gets the clock in and clock out times for the employee that day
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
    //returns the total hours worked by the employee
    public double getTotalHours(){
        return hr;
    }
    //TODO NEEDS TO BE CALLED AFTER LAST CLOCK OUT FOR EACH EMPLOYEE
    //adds hours worked to the employee, then updates the sick day hours of the employee
    public void signOut(){
        double a = stringToDouble(hours.get(hours.size() - 8));
        double b = stringToDouble(hours.get(hours.size() - 7));
        double c = stringToDouble(hours.get(hours.size() - 6));
        double d = stringToDouble(hours.get(hours.size() - 5));
        double e = stringToDouble(hours.get(hours.size() - 4));
        double f = stringToDouble(hours.get(hours.size() - 3));
        double g = stringToDouble(hours.get(hours.size() - 2));
        double h = stringToDouble(hours.get(hours.size()-1));
        double t = a-b+c-d+e-f+g-h;
        hr+=t;
        shr = hr/30;
        if(shr > shrMax){
            shr = shrMax;
        }
    }
    private double stringToDouble(String rawHours) {
        double result = 0;
        result += Double.parseDouble(rawHours.split(":")[0]);
        result += Double.parseDouble(rawHours.split(":")[1])/60.0;
        result += Double.parseDouble(rawHours.split(":")[2])/3600.0;
        return result;
    }
    //returns the sick day hour max
    public double getShrMax(){
        return shrMax;
    }
    //returns the picture of employee
    public Sprite getPic(){
        return pic;
    }
    //get normal work day hours
    public double getOvt(){
        return nwd;
    }
    //organized printing method
    @Override
    public String toString(){
        String sa = String.valueOf(salary);
        String sdr = String.valueOf(shr);
        String sdhMax = String.valueOf(shrMax);
        String vdr = String.valueOf(vD);
        String thw = String.valueOf(hr);
        String s =
                "Employee name: " + name + "\n" +
                        "Employee Salary: " +sa + "\n" +
                        "Sick day hours remaining: " + sdr + "\n" +
                        "Maximum sick day hours: " + sdhMax + "\n" +
                        "Vacation days remaining: " +vdr + "\n" +
                        "Total hours worked: " + thw + "\n" +
                        "Benefits: " + benefits;
        return s;
    }

    public void setVD(int e){
        vD = e;
    }
}