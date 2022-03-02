package com.example.carpoolbuddy;

import java.util.ArrayList;

/**
 * Class for segway, which extends Vehicle
 * A segway should have a range and a weight capacity
 *
 * @author Myriam Lynch
 * @version 1.0
 */

public class Segway extends Vehicle
{
    private int range;
    private int weightCapacity;

    public Segway(String own, String mod, int cap, String vID, boolean op, String vType,
                  double bPrice, int r, int wCap)
    {
        super(own, mod, cap, vID, op, vType, bPrice);
        range = r;
        weightCapacity = wCap;
    }

    public Segway (String own, String mod, int cap, String vID, boolean op, String vType,
                   double bPrice, ArrayList<String> rID, int r, int wCap)
    {
        super(own, mod, cap, vID, op, vType, bPrice, rID);
        range = r;
        weightCapacity = wCap;
    }

    //getters and setters

    public int getRange()
    {
        return range;
    }

    public void setRange(int range)
    {
        this.range = range;
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
        return "Segway{" +
                "range=" + range +
                ", weightCapacity=" + weightCapacity +
                '}';
    }
}
