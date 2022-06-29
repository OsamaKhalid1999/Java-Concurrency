package com.mycompany.NickWasGridlocked;


import java.util.Objects;


//most of the junctions are same so main comments are in Junction A
import static java.lang.Thread.sleep;

public class JunctionA implements Runnable, IJunction {
    //junction attributes
    private final String name;
    private final int greenLightTime;
    private final customClock clock;
    private final Road [] entries;
    private final Road [] exits;

    public JunctionA(String name, int greenLightTime, customClock clock, Road[] entryRoads, Road[] exitRoads) {
        this.name = name;
        this.greenLightTime = greenLightTime;
        this.clock = clock;
        this.entries = entryRoads;
        this.exits = exitRoads;
    }


    public void run()
    {       
        while(clock.ticksCount() < StaticClockVariables.simulationTime)
        {
            try {
                sleep( (int) (Math.random() * 5));
            } catch (InterruptedException ex) {
                //
            }
            vehicleMove();
        }        
    }
    
    public void vehicleMove()//method that decide the traffic light timings and car coming, passing and waiting in the junction
    {
        // When junction is "A".
        if (Objects.equals(name, "JunctionA"))
        {
            for (Road roadIn : entries)//looping int each road that enter to the junction A
            {
                //count of current clock and greenlight timings and number of cars passing
                int start = clock.ticksCount();
                int end = start + greenLightTime;
                int carThrough = 0;

                while (clock.ticksCount() < end && clock.ticksCount() < StaticClockVariables.simulationTime)
                {                    
                    Vehicle car;

                    boolean isThrough = false;

                    while ( !isThrough && clock.ticksCount() < end && clock.ticksCount() < StaticClockVariables.simulationTime
                            && (clock.ticksCount() + StaticClockVariables.carThroughSeconds) < end)
                    {                   
                        System.out.print("");
                        if ( roadIn.roads[roadIn.nextOut] != null)
                        {
                            if (Objects.equals(roadIn.roads[roadIn.nextOut].getDestination(),
                                    StaticCarParkVariables.IndustrialEstate))//if the destination is on the west
                            {
                                for ( Road roadOut : exits)//looping through the exit roads from junction A
                                {                                    
                                    if (Objects.equals(roadOut.name, StaticRoadVariables.roadA2))
                                    {                                       
                                        if (roadOut.carsOnRoad < roadOut.roads.length)
                                        {
                                            if (roadIn.carsOnRoad > 0)
                                            {
                                                car =roadIn.removeVehicles();//junctions acts as a consumer
                                                if ( car != null)
                                                {
                                                    try {
                                                        sleep(StaticClockVariables.carThroughTime);
                                                    } catch (InterruptedException ex) {
                                                        //
                                                    }
                                                    roadOut.generateVehicles(car, clock);//junction acts like a producer
                                                    isThrough = true;
                                                    carThrough++;
                                                }
                                            } 
                                        }
                                    }
                                }            
                            }
                            else 
                            {
                                for ( Road roadOut : exits)//looping through exit Anorth
                                {
                                    if (Objects.equals(roadOut.name, StaticRoadVariables.roadA3))
                                    {                                        
                                        if (roadOut.carsOnRoad < roadOut.roads.length)
                                        {
                                            if (roadIn.carsOnRoad > 0)
                                            {
                                                car =roadIn.removeVehicles();//consumer
                                                if ( car != null)
                                                {
                                                    try {
                                                        sleep(StaticClockVariables.carThroughTime);
                                                    } catch (InterruptedException ex) {
                                                        //
                                                    }
                                                    roadOut.generateVehicles(car, clock);//producer
                                                    isThrough = true;
                                                    carThrough++;
                                                }
                                            } 
                                        }
                                    }
                                }
                            }
                        }
                    }
                }


                //output to console

                if (carThrough > 0 || roadIn.carsOnRoad == 0 )
                {
                    System.out.println("Time : " + clock.minutes() + "m:" + clock.seconds()
                            + "s " + this.name + ": " + carThrough + " cars from " +
                            roadIn.name + "," + roadIn.carsOnRoad + " cars waiting."  );
                }
                else
                {
                    System.out.println("Time : " + clock.minutes() + "m:" + clock.seconds()
                            + "s " + this.name + ": " + carThrough + " cars from " +
                            roadIn.name + "," + roadIn.carsOnRoad + " cars waiting. Gridlocked!"  );
                }
            }
        }
    
             
    }   
}
