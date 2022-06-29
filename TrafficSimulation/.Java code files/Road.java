package com.mycompany.NickWasGridlocked;



import java.util.Arrays;
import java.util.concurrent.Semaphore;


class Road implements IRoad//buffer class
{
    private final Semaphore mutex = new Semaphore(1);//lock semaphore between producer and consumer
    private final Semaphore spaces;//available slots in buffer
    private final Semaphore items;//count items in buffer
    
    public String name;
    public Vehicle [] roads;
    public int nextIn = 0;
    public int nextOut = 0;
   
    public int carsOnRoad = 0;  
    public customClock clock;

    public Road(String name, int size, customClock clock)
    {
        roads = new Vehicle[size];
        spaces = new Semaphore(size);
        items = new Semaphore(0);
        this.name = name;
        this.clock = clock;
    }

    public Vehicle[] getRoads() {
        return roads;
    }

     public void generateVehicles(Vehicle aVehicle, customClock clock)
     {       
         try {
            spaces.acquire();
            mutex.acquire();//wait
        } catch (InterruptedException ex) {
            //ex.printStackTrace();
        }
             //add vehicle to buffer
        roads[nextIn] = aVehicle;
        carsOnRoad++;
        try {
            Thread.sleep((int) (Math.random() * 10));
        } catch (InterruptedException ignored) {
        }
        nextIn++;
        
        if(nextIn == getRoads().length){
            nextIn = 0;
        }
        //signal
         mutex.release();//
         items.release();

        
    }
     
     
     public  Vehicle removeVehicles()
    {
        new Vehicle();
        Vehicle car;
        car = null;

        //wait
         try {
             for (Semaphore semaphore : Arrays.asList(items, mutex)) {
                 semaphore.acquire();
             }
         } catch (InterruptedException ex) {
            //ex.printStackTrace();
        }
         //remove vehicles from road
        if (clock.ticksCount() < StaticClockVariables.simulationTime)
        {
            car = roads[nextOut];
            try {
                //Thread.interrupted((Math.random() * 10));
                Thread.sleep((int) (Math.random() * 10));
            } catch (InterruptedException ignored) {
            }
            carsOnRoad--;                   
            nextOut++;       
            
            if (nextOut== roads.length)
            {
                nextOut=0;
            }       
        }
        //signal

        mutex.release();
        spaces.release();

        return car;
    }   
}
