package com.mycompany.NickWasGridlocked;



import static java.lang.Thread.sleep;


public class CarPark implements Runnable, IcarPark//consumer class
{
    //car park attributes
    private final String carParkName;
    private final Vehicle [] carParkSpaces;
    private int availableSpaceCarPark;
    private final Road road;
    private final customClock clock;
    private int nextIn = 0;
    private int carParked = 0;

    public CarPark(String name, int availableSpaceCarPark, Road road, customClock clock)
    {
        this.carParkName = name;
        this.availableSpaceCarPark = availableSpaceCarPark;
        this.carParkSpaces = new Vehicle[availableSpaceCarPark];
        this.clock = clock;
        this.road = road;
    }   
    
    public void roadToCarPark()//this method will insert the car from a road to car park
    {
        Vehicle cars = new Vehicle();
        cars = road.removeVehicles();//buffer extract method
        
        if (cars != null)
        {
            try {
                    sleep(StaticClockVariables.parkingTime);
                } catch (InterruptedException ex) {
                    //
                }            
            carParkSpaces[nextIn] = cars;
            nextIn++;
            availableSpaceCarPark--;
            carParked++;            
            cars.setParkingTime(clock.ticksCount());
        }
    }   

    @Override
    public void run()
    {
        while(clock.ticksCount() < StaticClockVariables.simulationTime && availableSpaceCarPark > 0
                && (clock.ticksCount() + StaticClockVariables.parkingSeconds + 1) < StaticClockVariables.simulationTime){
            //checking if in simulation time and if there free slots in the car parks
            roadToCarPark();
            try {
                sleep( (int) (Math.random() * 5));
            } catch (InterruptedException ignored) {
            }
        }
        
    }

    public String carParkName()
    {
        return carParkName;
    }
    public int availableSpaces()
    {
        return availableSpaceCarPark;
    }
    public Vehicle[] carParkSpaces() {
        return carParkSpaces;
    }
    public int carParked() {
        return carParked;
    }
    
    
}  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

  
    
    
