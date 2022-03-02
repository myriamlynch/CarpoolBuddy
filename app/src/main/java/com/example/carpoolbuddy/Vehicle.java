package com.example.carpoolbuddy;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Class for vehicle, which implements Serializable
 * A vehicle should have
 * - an owner
 * - a model
 * - a capacity
 * - a vehicle ID
 * - an arraylist of riders IDs
 * - a boolean to determine if vehicle is open
 * - a type
 * - a base price
 * - an indication of whether the vehicle is electric
 *
 * @author Myriam Lynch
 * @version 1.0
 */

public class Vehicle implements Serializable
{
    private String owner;
    private String model;
    private int capacity;
    private String vehicleID;
    private ArrayList<String> ridersUIDs;
    private boolean open;
    private String vehicleType;
    private double basePrice;
    private boolean isElectric;

    public Vehicle(String own, String mod, int cap, String vID, boolean op,
                   String vType, double bPrice)
    {
        owner = own;
        model = mod;
        capacity = cap;
        vehicleID = vID;
        open = op;
        vehicleType = vType;
        basePrice = bPrice;
    }

    public Vehicle(String own, String mod, int cap, String vID, boolean op, String vType,
                   double bPrice, boolean elec)
    {
        owner = own;
        model = mod;
        capacity = cap;
        vehicleID = vID;
        open = op;
        vehicleType = vType;
        basePrice = bPrice;
        isElectric = elec;
    }

    public Vehicle(String id, String m, String type, int cap, double price, String status)
    {
        vehicleID = id;
        model = m;
        vehicleType = type;
        capacity = cap;
        basePrice = price;
        if(status.equals("true"))
            open = true;
        else
            open = false;
    }

    public Vehicle(String id, String m, String type, int cap, double price,
                   String status, boolean elec)
    {
        vehicleID = id;
        model = m;
        vehicleType = type;
        capacity = cap;
        basePrice = price;
        if(status.equals("true"))
            open = true;
        else
            open = false;
        isElectric = elec;
    }

    public Vehicle(String own, String mod, int cap, String vID, boolean op, String vType,
                   double bPrice, ArrayList<String> rID)
    {
        owner = own;
        model = mod;
        capacity = cap;
        vehicleID = vID;
        open = op;
        vehicleType = vType;
        basePrice = bPrice;
        ridersUIDs = rID;
    }

    public Vehicle(String own, String mod, int cap, String vID, boolean op, String vType,
                   double bPrice, ArrayList<String> rID, boolean elec)
    {
        owner = own;
        model = mod;
        capacity = cap;
        vehicleID = vID;
        open = op;
        vehicleType = vType;
        basePrice = bPrice;
        ridersUIDs = rID;
        isElectric = elec;
    }

    //getters and setters

    public boolean isElectric()
    {
        return isElectric;
    }

    public void setElectric(boolean electric)
    {
        isElectric = electric;
    }

    public String getOwner()
    {
        return owner;
    }

    public void setOwner(String owner)
    {
        this.owner = owner;
    }

    public String getModel()
    {
        return model;
    }

    public void setModel(String model)
    {
        this.model = model;
    }

    public int getCapacity()
    {
        return capacity;
    }

    public void setCapacity(int capacity)
    {
        this.capacity = capacity;
    }

    public String getVehicleID()
    {
        return vehicleID;
    }

    public void setVehicleID(String vehicleID)
    {
        this.vehicleID = vehicleID;
    }

    public ArrayList<String> getRidersUIDs()
    {
        return ridersUIDs;
    }

    public void setRidersUIDs(ArrayList<String> ridersUIDs)
    {
        this.ridersUIDs = ridersUIDs;
    }

    public void addRidersUID(String str)
    {
        ridersUIDs.add(str);
    }

    public boolean isOpen()
    {
        return open;
    }

    public void setOpen(boolean open)
    {
        this.open = open;
    }

    public String getVehicleType()
    {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType)
    {
        this.vehicleType = vehicleType;
    }

    public double getBasePrice()
    {
        return basePrice;
    }

    public void setBasePrice(double basePrice)
    {
        this.basePrice = basePrice;
    }

    @Override
    public String toString()
    {
        return "Vehicle{" +
                "owner='" + owner + '\'' +
                ", model='" + model + '\'' +
                ", capacity=" + capacity +
                ", vehicleID='" + vehicleID + '\'' +
                ", ridersUIDs=" + ridersUIDs +
                ", open=" + open +
                ", vehicleType='" + vehicleType + '\'' +
                ", basePrice=" + basePrice +
                '}';
    }
}
