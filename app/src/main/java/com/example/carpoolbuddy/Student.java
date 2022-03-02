package com.example.carpoolbuddy;

import java.util.ArrayList;

/**
 * Class for Student, which extends User class
 * A student should have a graduating year and an arraylist of parents IDs
 *
 * @author Myriam Lynch
 * @version 1.0
 */

public class Student extends User
{
    private String graduatingYear;
    private ArrayList<String> parentUIDs;

    public Student(String id, String nm, String emailAddress, String type, double pMultiplier,
                   ArrayList<String> a, ArrayList<String> b, String year)
    {
        super(id, nm, emailAddress, type, pMultiplier, a);
        parentUIDs = b;
        graduatingYear = year;
    }

    public Student(String id, String nm, String emailAddress, String type, double pMultiplier,
                   String year)
    {
        super(id, nm, emailAddress, type, pMultiplier);
        parentUIDs = new ArrayList<String>();
        graduatingYear = year;
    }

    //getters and setters

    public String getGraduatingYear()
    {
        return graduatingYear;
    }

    public void setGraduatingYear(String graduatingYear)
    {
        this.graduatingYear = graduatingYear;
    }

    public ArrayList<String> getParentUIDs()
    {
        return parentUIDs;
    }

    public void setParentUIDs(ArrayList<String> parentUIDs)
    {
        this.parentUIDs = parentUIDs;
    }

    public void addParentUIDs(String str)
    {
        parentUIDs.add(str);
    }

    @Override
    public String toString() {
        return "Student{" +
                "graduatingYear='" + graduatingYear + '\'' +
                ", parentUIDs=" + parentUIDs +
                '}';
    }
}
