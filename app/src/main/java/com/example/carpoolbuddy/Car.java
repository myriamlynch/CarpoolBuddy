package com.example.carpoolbuddy;

import java.util.ArrayList;

/**
 * This class is for a car, which extends the Vehicle class
 * A car should have a range
 *
 * @author Myriam Lynch
 * @version 1.0
 */

public class Car extends Vehicle
{
    private int range;

    public Car(String own, String mod, int cap, String vID, boolean op, String vType, double bPrice,
               int r)
    {
        super(own, mod, cap, vID, op, vType, bPrice);
        range = r;
    }

    public Car(String own, String mod, int cap, String vID, boolean op, String vType, double bPrice,
               ArrayList<String> rID,  int r)
    {
        super(own, mod, cap, vID, op, vType, bPrice, rID);
        range = r;
    }

    //getters and setters methods

    public int getRange()
    {
        return range;
    }

    public void setRange(int range)
    {
        this.range = range;
    }

    @Override
    public String toString()
    {
        return "Car{" +
                "range=" + range +
                '}';
    }
}
