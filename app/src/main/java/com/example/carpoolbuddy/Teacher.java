package com.example.carpoolbuddy;

import java.util.ArrayList;

/**
 * Class for teacher, which extends User class
 * A teacher should have a title
 *
 * @author Myriam Lynch
 * @version 1.0
 */

public class Teacher extends User
{
    private String inSchoolTitle;

    public Teacher(String id, String nm, String emailAddress, String type, String title)
    {
        super(id, nm, emailAddress, type);
        inSchoolTitle = title;
    }

    public Teacher(String id, String nm, String emailAddress, String type, double pMult,
                   ArrayList<String> a, String title)
    {
        super(id, nm, emailAddress, type, pMult, a);
        inSchoolTitle = title;
    }

    //getters and setters methods

    public String getInSchoolTitle()
    {
        return inSchoolTitle;
    }

    public void setInSchoolTitle(String inSchoolTitle)
    {
        this.inSchoolTitle = inSchoolTitle;
    }

    @Override
    public String toString()
    {
        return "Teacher{" +
                "inSchoolTitle='" + inSchoolTitle + '\'' +
                '}';
    }
}
