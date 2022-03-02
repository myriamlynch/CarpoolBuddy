package com.example.carpoolbuddy;

import java.util.ArrayList;

/**
 * This class is for a helicopter, which extends the Vehicle class
 * A helicopter should have a maximum altitude and a minimum altitude
 *
 * @author Myriam Lynch
 * @version 1.0
 */

public class Helicopter extends Vehicle
{
    private int maxAltitude;
    private int maxAirSpeed;

    public Helicopter(String own, String mod, int cap, String vID, boolean op, String vType,
                      double bPrice, int alt, int speed)
    {
        super(own, mod, cap, vID, op, vType, bPrice);
        maxAltitude = alt;
        maxAirSpeed = speed;
    }

    public Helicopter(String own, String mod, int cap, String vID, boolean op, String vType,
                      double bPrice, ArrayList<String> rID, int alt, int speed)
    {
        super(own, mod, cap, vID, op, vType, bPrice, rID);
        maxAltitude = alt;
        maxAirSpeed = speed;
    }

    //getters and setters methods

    public int getMaxAltitude()
    {
        return maxAltitude;
    }

    public void setMaxAltitude(int maxAltitude)
    {
        this.maxAltitude = maxAltitude;
    }

    public int getMaxAirSpeed()
    {
        return maxAirSpeed;
    }

    public void setMaxAirSpeed(int maxAirSpeed)
    {
        this.maxAirSpeed = maxAirSpeed;
    }

    @Override
    public String toString()
    {
        return "Helicopter{" +
                "maxAltitude=" + maxAltitude +
                ", maxAirSpeed=" + maxAirSpeed +
                '}';
    }
}
