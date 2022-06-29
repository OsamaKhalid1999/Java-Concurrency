package com.mycompany.NickWasGridlocked;

public class Vehicle implements IVehicle//resource class
{
    String name;
    private String destination;
    private int entryTime;    
    private int parkingTime;
    
 
    public Vehicle(String name,String destination, customClock clock)
    {
        this.name = name;
        this.destination = destination;
        this.entryTime = clock.ticksCount();
        
    }
    
    public Vehicle() {
    }

    public void setParkingTime(int parkingTime) {
        this.parkingTime = parkingTime;
    }

    public String getDestination() {
        return destination;
    }

    public int getEntryTime() {
        return entryTime;
    }

    public int getParkingTime() {
        return parkingTime;
    }   
}
