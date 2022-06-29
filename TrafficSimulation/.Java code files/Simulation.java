package com.mycompany.NickWasGridlocked;

import java.util.ArrayList;
import java.util.List;
import java.lang.Thread;


public class Simulation {

    public Simulation() {

    };

    public static void main(String[] args)
    {
        customClock myClock = new customClock(0,0);//clock object

        List<Parking> parking = new ArrayList<>();//list of destinations



        //creating destinations according to their weight and add them to the list
        Parking University = new Parking(StaticCarParkVariables.University, StaticCarParkValues.universityValue);
        Parking Station = new Parking(StaticCarParkVariables.Station, StaticCarParkValues.stationValue);
        Parking ShoppingCentre = new Parking(StaticCarParkVariables.ShoppingCentre, StaticCarParkValues.shoppingCentreValue);
        Parking IndustrialEstate = new Parking(StaticCarParkVariables.IndustrialEstate, StaticCarParkValues.industrialEstateValue);

        parking.add(University);
        parking.add(Station);
        parking.add(ShoppingCentre);
        parking.add(IndustrialEstate);


        //road object
        Road roadA1 = new Road(StaticRoadVariables.roadA1, 60, myClock);//south
        Road roadA2 = new Road(StaticRoadVariables.roadA2, 15, myClock);//west
        Road roadA3 = new Road(StaticRoadVariables.roadA3, 7, myClock);//north

        Road roadB1 = new Road(StaticRoadVariables.roadB1, 7, myClock);//south
        Road roadB2 = new Road(StaticRoadVariables.roadB2, 30, myClock);//east
        Road roadB3 = new Road(StaticRoadVariables.roadB3, 10, myClock);//north


        Road roadC1 = new Road(StaticRoadVariables.roadC1, 7, myClock);//south
        Road roadC2 = new Road(StaticRoadVariables.roadC2, 10, myClock);//west
        Road roadC3 = new Road(StaticRoadVariables.roadC3, 50, myClock);//north
        Road roadC4 = new Road(StaticRoadVariables.roadC4, 10, myClock);//east


        Road roadD1 = new Road(StaticRoadVariables.roadD1, 15, myClock);//south
        Road roadD2 = new Road(StaticRoadVariables.roadD2, 15, myClock);//north

        //road arrays of total roads and entry and exit roads
        Road [] inToA = {roadA1, roadB1};
        Road [] outToA = {roadA2, roadA3};

        Road [] inToB = {roadA3, roadB2, roadC4};
        Road [] outToB = {roadB1, roadB3};

        Road [] inToC = {roadB3, roadC3};
        Road [] outToC = {roadC1, roadC2,roadC4};

        Road [] inToD = {roadC2};
        Road [] outToD = {roadD1, roadD2};

        Road [] totalRoads = {roadA2, roadA1, roadA3, roadB2, roadB1 , roadB3,roadC4, roadC2, roadC1, roadC3, roadD1, roadD2};



        //car park objects
        CarPark industrialPark = new CarPark(StaticCarParkVariables.IndustrialEstate, 1000, roadA2, myClock);
        CarPark shoppingCentre = new CarPark(StaticCarParkVariables.ShoppingCentre, 400, roadC1, myClock);
        CarPark station = new CarPark(StaticCarParkVariables.Station, 150, roadD1, myClock);
        CarPark university = new CarPark(StaticCarParkVariables.University, 100, roadD2, myClock);

        //scenario 1
       /* EntryPoint entrySouth = new EntryPoint("South EP", roadA1, 550, myClock, parking);
        EntryPoint entryEast = new EntryPoint("East EP", roadB2, 300, myClock, parking);
        EntryPoint entryNorth = new EntryPoint("North EP", roadC3, 550, myClock, parking);

        JunctionA A = new JunctionA("JunctionA", 60, myClock, inToA, outToA);
        JunctionB B = new JunctionB("JunctionB", 60, myClock, inToB, outToB);
        JunctionC C = new JunctionC("JunctionC", 30, myClock, inToC, outToC);
        JunctionD D = new JunctionD("JunctionD", 30, myClock, inToD, outToD);*/

        //scenario 5
        EntryPoint entrySouth = new EntryPoint("South EP", roadA1, 450, myClock, parking);
        EntryPoint entryEast = new EntryPoint("East EP", roadB2, 200, myClock, parking);
        EntryPoint entryNorth = new EntryPoint("North EP", roadC3, 450, myClock, parking);

        JunctionA A = new JunctionA("JunctionA", 90, myClock, inToA, outToA);
        JunctionB B = new JunctionB("JunctionB", 90, myClock, inToB, outToB);
        JunctionC C = new JunctionC("JunctionC", 90, myClock, inToC, outToC);
        JunctionD D = new JunctionD("JunctionD", 30, myClock, inToD, outToD);

        //scenario 2
       /* EntryPoint entrySouth = new EntryPoint("South EP", roadA1, 550, myClock, parking);
        EntryPoint entryEast = new EntryPoint("East EP", roadB2, 300, myClock, parking);
        EntryPoint entryNorth = new EntryPoint("North EP", roadC3, 550, myClock, parking);

        JunctionA A = new JunctionA("JunctionA", 30, myClock, inToA, outToA);
        JunctionB B = new JunctionB("JunctionB", 30, myClock, inToB, outToB);
        JunctionC C = new JunctionC("JunctionC", 20, myClock, inToC, outToC);
        JunctionD D = new JunctionD("JunctionD", 30, myClock, inToD, outToD);
*/
        //scenario 4
       /* EntryPoint entrySouth = new EntryPoint("South EP", roadA1, 450, myClock, parking);
        EntryPoint entryEast = new EntryPoint("East EP", roadB2, 100, myClock, parking);
        EntryPoint entryNorth = new EntryPoint("North EP", roadC3, 450, myClock, parking);

        JunctionA A = new JunctionA("JunctionA", 25, myClock, inToA, outToA);
        JunctionB B = new JunctionB("JunctionB", 25, myClock, inToB, outToB);
        JunctionC C = new JunctionC("JunctionC", 25, myClock, inToC, outToC);
        JunctionD D = new JunctionD("JunctionD", 30, myClock, inToD, outToD);*/

        //scenario 3
        //entry point objects
       /* EntryPoint entrySouth = new EntryPoint("South EP", roadA1, 250, myClock, parking);
        EntryPoint entryEast = new EntryPoint("East EP", roadB2, 300, myClock, parking);
        EntryPoint entryNorth = new EntryPoint("North EP", roadC3, 200, myClock, parking);

        JunctionA A = new JunctionA("JunctionA", 60, myClock, inToA, outToA);
        JunctionB B = new JunctionB("JunctionB", 60, myClock, inToB, outToB);
        JunctionC C = new JunctionC("JunctionC", 30, myClock, inToC, outToC);
        JunctionD D = new JunctionD("JunctionD", 30, myClock, inToD, outToD);
*/
        //total car parks array
        CarPark [] totalCarParks = {industrialPark, shoppingCentre, station, university};

        //total entry point array
        EntryPoint [] totalEntryPoints = {entrySouth, entryEast, entryNorth};

        //display track of remaining parkings
        ParkingTracker displaySign = new ParkingTracker(totalCarParks, myClock);

        ////////declare all the threads and start them
        Thread threadA=new Thread(A);
        threadA.start();

        Thread threadB=new Thread(B);
        threadB.start();

        Thread threadC=new Thread(C);
        threadC.start();

        Thread threadD=new Thread(D);
        threadD.start();

        Thread threadEntrySouth=new Thread(entrySouth);
        threadEntrySouth.start();

        Thread threadEntryEast=new Thread(entryEast);
        threadEntryEast.start();

        Thread threadEntryNorth=new Thread(entryNorth);
        threadEntryNorth.start();


        Thread threadIndustrialPark=new Thread(industrialPark);
        threadIndustrialPark.start();

        Thread threadShoppingCentre=new Thread(shoppingCentre);
        threadShoppingCentre.start();

        Thread threadStation=new Thread(station);
        threadStation.start();

        Thread threadUniversity=new Thread(university);
        threadUniversity.start();

        Thread threadDisplaySign=new Thread(displaySign);
        threadDisplaySign.start();

        Thread threadMyClock=new Thread(myClock);
        threadMyClock.start();

        try {

            while( myClock.ticksCount() < StaticClockVariables.simulationTime)
            {
                //join threads while simulation
                threadEntrySouth.join();
                threadEntryEast.join();
                threadEntryNorth.join();

                threadA.join();
                threadB.join();
                threadC.join();
                threadD.join();

                threadIndustrialPark.join(1200);
                threadShoppingCentre.join(1200);
                threadStation.join(1200);
                threadUniversity.join(1200);

                threadDisplaySign.join();

                if (myClock.ticksCount() >= StaticClockVariables.simulationTime)
                {
                    //stop all the run threads when the simulation time is finished
                    threadIndustrialPark.interrupt();
                    threadShoppingCentre.interrupt();
                    threadStation.interrupt();
                    threadUniversity.interrupt();

                    threadEntrySouth.interrupt();
                    threadEntryEast.interrupt();
                    threadEntryNorth.interrupt();

                    threadA.interrupt();
                    threadB.interrupt();
                    threadC.interrupt();
                    threadD.interrupt();

                }
            }
        } catch (InterruptedException ex)
        {
            // Logger.getLogger(Gridlock.class.getName()).log(Level.SEVERE, null, ex);
        }


        for (CarPark carPark : totalCarParks)//displaying average journey time for each car created
        {
            int totalSecTakenByAllCars = 0;
            for (int i = 0; i < carPark.carParked(); i++)
            {
                int counter = carPark.carParkSpaces()[i].getParkingTime() - carPark.carParkSpaces()[i].getEntryTime();
                totalSecTakenByAllCars += counter;
            }
            double aveTotalSec = totalSecTakenByAllCars/carPark.carParked();
            int minutes = (int) (aveTotalSec/ StaticClockVariables.minutes);
            int seconds = (int) (aveTotalSec % StaticClockVariables.minutes);
            System.out.println( carPark.carParkName() + " : " +carPark.carParked() + "car parked, average journey time " + minutes + "m" + seconds + "s"  );

        }
        ///calculating the number of cars produced, still on road and already parked
        int totalCars = 0 ;
        int carsStillTravelling = 0 ;
        int totalParkedCars = 0;

        for (EntryPoint totalEP : totalEntryPoints)
        {
            totalCars += totalEP.produced;
        }
        for (Road totalROAD : totalRoads)
        {
            carsStillTravelling += totalROAD.carsOnRoad;
        }
        for (CarPark totalCARPARK : totalCarParks)
        {
            totalParkedCars += totalCARPARK.carParked();
        }

        System.out.println("The number of cars that are still travelling is: " + carsStillTravelling);
        System.out.println("The number of parked cars are : " + totalParkedCars);
        System.out.println("The sum of the Generated cars during the simulation is : " + totalCars);

    }
}
