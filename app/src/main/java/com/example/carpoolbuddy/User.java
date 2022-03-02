package com.example.carpoolbuddy;

import java.util.ArrayList;

/**
 * Class for User
 * A user should have:
 * - an ID
 * - a name
 * - an email
 * - a type
 * - a price multiplier
 * - an arraylist of vehicles they own
 * - points
 *
 * @author Myriam Lynch
 * @version 1.0
 */

public class User
{
    private String uid;
    private String name;
    private String email;
    private String userType;
    private double priceMultiplier;
    private ArrayList<String> ownedVehicles;
    private int points;

    public User(String emailAdress)
    {
        email = emailAdress;
        ownedVehicles = new ArrayList<String>();
        points = 0;
    }

    public User(String id, String nm, String emailAddress, String type)
    {
        uid = id;
        name = nm;
        email = emailAddress;
        userType = type;
        priceMultiplier = 0;
        ownedVehicles = new ArrayList<String>();
        points = 0;
    }

    public User(String id, String nm, String emailAddress, String type, double pMultiplier)
    {
        uid = id;
        name = nm;
        email = emailAddress;
        userType = type;
        priceMultiplier = pMultiplier;
        ownedVehicles = new ArrayList<String>();
        points = 0;
    }

    public User(String id, String nm, String emailAddress, String type,
                double pMultiplier, ArrayList<String> a)
    {
        uid = id;
        name = nm;
        email = emailAddress;
        userType = type;
        priceMultiplier = pMultiplier;
        ownedVehicles = a;
        points = 0;
    }

    public User(String emailA, ArrayList<String> a)
    {
        email = emailA;
        ownedVehicles = a;
        points = 0;
    }

    //getters and setters methods

    public int getPoints()
    {
        return points;
    }

    public void setPoints(int points)
    {
        this.points = points;
    }

    public String getUid()
    {
        return uid;
    }

    public void setUid(String uid)
    {
        this.uid = uid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getUserType()
    {
        return userType;
    }

    public void setUserType(String userType)
    {
        this.userType = userType;
    }

    public double getPriceMultiplier()
    {
        return priceMultiplier;
    }

    public void setPriceMultiplier(double priceMultiplier)
    {
        this.priceMultiplier = priceMultiplier;
    }

    public ArrayList<String> getOwnedVehicles()
    {
        return ownedVehicles;
    }

    public void addVehicles(String vehicle)
    {
        ownedVehicles.add(vehicle);
    }

    @Override
    public String toString()
    {
        return "User{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", userType='" + userType + '\'' +
                ", priceMultiplier=" + priceMultiplier +
                ", ownedVehicles=" + ownedVehicles +
                '}';
    }
}
