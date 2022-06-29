package com.mycompany.NickWasGridlocked;



import static java.lang.Thread.sleep;


import java.util.List;

public class EntryPoint implements Runnable,IEntryPoint//producer class
{
    //entry point attributes
    private final String name;
    private final Road road;
    private final int numberOfCars;
    private final customClock clock;

    private final List<Parking> parking;//destinations
    int produced = 0;//produced cars
    double ratio;//production rate
    int production;//car to be produced

    public EntryPoint(String name, Road road, int numberOfCars, customClock clock, List<Parking> parking)
    {
        this.name  = name;
        this.numberOfCars = numberOfCars;
        this.road = road;
        this.clock = clock;
        this.parking = parking;
        ratio = ( (double) numberOfCars / (double) StaticClockVariables.simulationTime);
    }   

    public void createVehicles(Vehicle aVehicle)//create vehicle
    {
        road.generateVehicles(aVehicle, clock);//generate car and add to buffer
        produced++;
    }
    public int randomArrival(List<Parking> destination)//ask car its destination
    {
        double value = 0.0;
        for (Parking parking : destination) {
            value = value + parking.weight;
        }

        int count = 0;
        for (double sort = Math.random() * value; count < destination.size() - 1; count++) {
            sort -= destination.get(count).weight;
            if (sort <= 0.0) {
                break;
            }
        }
        return count;
    }



    @Override
    public void run(){
        while ( produced < numberOfCars && clock.ticksCount() < StaticClockVariables.simulationTime)
        {
            //checking if in simulation time and car produced are less than the number of current cars
            try 
            {
                sleep((int) (Math.random() * 5));                
            } 
            catch (InterruptedException ignored)
            {
            }

            production = (int) (clock.ticksCount() * ratio);
            for (int i = 0; i < (production - produced); i++ )//looping everytime a car is generated and a random destination is given
            {
                int count = randomArrival(parking);
                String parkings = parking.get(count).name;
                String vehicleName = "";
                vehicleName = name.substring(0,1) + produced + 1;
                Vehicle Car = new Vehicle(vehicleName,parkings, clock);//vehcile object
                if ( road.carsOnRoad < road.roads.length)
                {
                    createVehicles(Car);//create vehicle
                }   
            }
        }        
    }
}