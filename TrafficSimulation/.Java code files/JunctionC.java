package com.mycompany.NickWasGridlocked;


import java.util.Objects;



import static java.lang.Thread.sleep;

public class JunctionC implements Runnable, IJunction {
    //attributes
    private final String name;
    private final int greenLightTime;
    private final customClock clock;
    private final Road [] entries;
    private final Road [] exits;

    public JunctionC(String name, int greenLightTime, customClock clock, Road[] entryRoads, Road[] exitRoads) {
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
    
    public void vehicleMove()
    {
        // When junction is "C".
        if (Objects.equals(name, "JunctionC"))
        {
            for (Road roadIn : entries)//loop through entry roads
            {                
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
                            if (Objects.equals(roadIn.roads[roadIn.nextOut].getDestination(), StaticCarParkVariables.IndustrialEstate))
                            {
                                for ( Road roadOut : exits)
                                {                                    
                                    if (Objects.equals(roadOut.name, StaticRoadVariables.roadC4))
                                    {                                       
                                        if (roadOut.carsOnRoad < roadOut.roads.length)
                                        {
                                            if (roadIn.carsOnRoad > 0)
                                            {
                                                car =roadIn.removeVehicles();
                                                if ( car != null)
                                                {
                                                    try {
                                                        sleep(StaticClockVariables.carThroughTime);
                                                    } catch (InterruptedException ex) {
                                                        //
                                                    }
                                                    roadOut.generateVehicles(car, clock);
                                                    isThrough = true;
                                                    carThrough++;
                                                }
                                            }
                                        }
                                    }
                                }            
                            }
                            else if (Objects.equals(roadIn.roads[roadIn.nextOut].getDestination(), StaticCarParkVariables.ShoppingCentre))
                            //if the destination is shopping centre, situated on west side
                            {
                                for ( Road roadOut : exits)
                                {
                                    if (Objects.equals(roadOut.name, StaticRoadVariables.roadC1))
                                    {                                        
                                        if (roadOut.carsOnRoad < roadOut.roads.length)
                                        {
                                            if (roadIn.carsOnRoad > 0)
                                            {
                                                car =roadIn.removeVehicles();
                                                if ( car != null)
                                                {
                                                    try {
                                                        sleep(StaticClockVariables.carThroughTime);
                                                    } catch (InterruptedException ex) {
                                                        //
                                                    }
                                                    roadOut.generateVehicles(car, clock);
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
                                for ( Road roadOut : exits)
                                {
                                    if (Objects.equals(roadOut.name, StaticRoadVariables.roadC2))
                                    {                                        
                                        if (roadOut.carsOnRoad < roadOut.roads.length)
                                        {
                                            if (roadIn.carsOnRoad > 0)
                                            {
                                                car =roadIn.removeVehicles();
                                                if ( car != null)
                                                {
                                                    try {
                                                        sleep(StaticClockVariables.carThroughTime);
                                                    } catch (InterruptedException ex) {
                                                        //
                                                    }
                                                    roadOut.generateVehicles(car, clock);
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
