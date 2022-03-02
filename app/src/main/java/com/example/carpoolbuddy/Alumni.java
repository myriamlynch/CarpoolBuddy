package com.example.carpoolbuddy;

import java.util.ArrayList;

/**
 * This class is for an alumni, which extends the User class
 * An alumni should have a graduation year
 *
 * @author Myriam Lynch
 * @version 1.0
 */

public class Alumni extends User
{
    private String graduateYear;

    public Alumni(String id, String nm, String emailAddress, String type, double pMultiplier,
                  ArrayList<String> a, String year)
    {
        super(id, nm, emailAddress, type, pMultiplier, a);
        graduateYear = year;
    }

    public Alumni(String id, String nm, String emailAddress, String type, double pMultiplier,
                  String year)
    {
        super(id, nm, emailAddress, type, pMultiplier);
        graduateYear = year;
    }

    //getter and setter methods
    public String getGraduateYear()
    {
        return graduateYear;
    }

    public void setGraduateYear(String graduateYear)
    {
        this.graduateYear = graduateYear;
    }

    @Override
    public String toString()
    {
        return "Alumni{" +
                "graduateYear='" + graduateYear + '\'' +
                '}';
    }
}
