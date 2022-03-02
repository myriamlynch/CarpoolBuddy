package com.example.carpoolbuddy;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * This class is for a parent, which extends the User class
 * A parent should have an array list of childrens IDs
 *
 * @author Myriam Lynch
 * @version 1.0
 */

public class Parent extends User
{
    private ArrayList<String> childrenUIDs;

    public Parent(String id, String nm, String emailAddress, String type)
    {
        super(id, nm, emailAddress, type);
        childrenUIDs = new ArrayList<String>();
    }

    public Parent(String id, String nm, String emailAddress, String type, ArrayList<String> a)
    {
        super(id, nm, emailAddress, type);
        childrenUIDs = a;
    }

    public Parent(String id, String nm, String emailAddress, String type, double pMultiplier,
                  ArrayList<String> a, ArrayList<String> b)
    {
        super(id, nm, emailAddress, type, pMultiplier, a);
        childrenUIDs = b;
    }

    //getters and setters methods

    public ArrayList<String> getChildrenUIDs()
    {
        return childrenUIDs;
    }

    public void setChildrenUIDs(ArrayList<String> childrenUIDs)
    {
        this.childrenUIDs = childrenUIDs;
    }

    public void addChildrenUIDs(String s)
    {
        childrenUIDs.add(s);
    }

    @Override
    public String toString() {
        return "Parent{" +
                "childrenUIDs=" + childrenUIDs +
                '}';
    }
}
