



package com.mycompany.NickWasGridlocked;


import java.util.Objects;




import static java.lang.Thread.sleep;

public class JunctionD implements Runnable, IJunction {

    //attributes
    private final String name;
    private final int greenLightTime;
    private final customClock clock;
    private final Road [] entries;
    private final Road [] exits;

    public JunctionD(String name, int greenLightTime, customClock clock, Road[] entryRoads, Road[] exitRoads) {
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
        // When junction is "D".
        if(Objects.equals(name, "JunctionD"))
        {
            for (Road roadIn : entries)
            {
                int start = clock.ticksCount(); // start time
                int end = start + greenLightTime; //end time
                int carThrough = 0;

                while (clock.ticksCount() < end && clock.ticksCount() < StaticClockVariables.simulationTime)
                {

                    Vehicle car;
                    boolean isThrough = false;

                    while ( !isThrough && clock.ticksCount() < end && clock.ticksCount() < StaticClockVariables.simulationTime
                            && (clock.ticksCount() + StaticClockVariables.carThroughSeconds) < end)
                    {
                        System.out.print(""); // Without this print statement not working
                            if (roadIn.roads[roadIn.nextOut] != null&&Objects.equals(roadIn.roads[roadIn.nextOut].getDestination(), StaticCarParkVariables.University))
                                //university is on the west side from junction D

                                for ( Road roadOut : exits)//looping through all exit roads
                                {
                                    if (Objects.equals(roadOut.name, StaticRoadVariables.roadD2)&&roadOut.carsOnRoad < roadOut.roads.length
                                    &&roadIn.carsOnRoad > 0 )
                                    {
                                        car =roadIn.removeVehicles();//consume car
                                        if ( car != null)
                                        {
                                            try {
                                                sleep(StaticClockVariables.carThroughTime);
                                            } catch (InterruptedException ex) {
                                                //
                                            }
                                            roadOut.generateVehicles(car, clock);//producer resource
                                            isThrough = true;
                                            carThrough++;
                                        }
                                    }
                                }

                            else
                            {
                                for ( Road roadOut : exits)
                                {
                                    if (Objects.equals(roadOut.name, StaticRoadVariables.roadD1)&&roadOut.carsOnRoad < roadOut.roads.length
                                        &&roadIn.carsOnRoad > 0)
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




