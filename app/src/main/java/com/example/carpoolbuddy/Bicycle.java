package com.example.carpoolbuddy;

import java.util.ArrayList;

/**
 * This class is for a bicycle, which extends the Vehicle class
 * A bicycle should have a type, weight and weight capacity
 *
 * @author Myriam Lynch
 * @version 1.0
 */

public class Bicycle extends Vehicle
{
    private String bicycleType;
    private int weight;
    private int weightCapacity;

    public Bicycle(String own, String mod, int cap, String vID, boolean op, String vType ,
                   double bPrice, String bType, int w, int wCap)
    {
        super(own, mod, cap, vID, op, vType, bPrice);
        bicycleType = bType;
        weight = w;
        weightCapacity = wCap;
    }

    public Bicycle(String own, String mod, int cap, String vID, boolean op, String vType ,
                   double bPrice, ArrayList<String> rID, String bType, int w, int wCap)
    {
        super(own, mod, cap, vID, op, vType, bPrice, rID);
        bicycleType = bType;
        weight = w;
        weightCapacity = wCap;
    }

    //getters and setters methods

    public String getBicycleType()
    {
        return bicycleType;
    }

    public void setBicycleType(String bicycleType)
    {
        this.bicycleType = bicycleType;
    }

    public int getWeight()
    {
        return weight;
    }

    public void setWeight(int weight)
    {
        this.weight = weight;
    }

    public int getWeightCapacity()
    {
        return weightCapacity;
    }

    public void setWeightCapacity(int weightCapacity)
    {
        this.weightCapacity = weightCapacity;
    }

    @Override
    public String toString()
    {
        return "Bicycle{" +
                "bicycleType='" + bicycleType + '\'' +
                ", weight=" + weight +
                ", weightCapacity=" + weightCapacity +
                '}';
    }
}
