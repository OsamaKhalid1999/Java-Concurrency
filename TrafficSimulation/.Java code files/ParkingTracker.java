
package com.mycompany.NickWasGridlocked;

import static java.lang.Thread.sleep;

public class ParkingTracker implements Runnable, IParkingTracker {//class keep track of some journey details
    
    private final CarPark [] carParks;
    private final customClock clock;
    private boolean isDisplaying = false;

    public ParkingTracker(CarPark[] carParks, customClock clock) {
        this.carParks = carParks;
        this.clock = clock;
    }
    
    public void run()
    {
        while(clock.ticksCount() < StaticClockVariables.simulationTime){
            try {
                sleep( (int) (Math.random() * 5));
            } catch (InterruptedException ex) {
            }
             //if simulation time is 10 minute, display available parking
            if ( !(clock.ticksCount() % 600 == 0) && clock.ticksCount() > 1 && isDisplaying == true)
            {
                isDisplaying = false;
            
            }            
            
            else if ( clock.ticksCount() % 600 == 0 && clock.ticksCount() > 1 && isDisplaying == false)
            {
                freeParkingSlots();
                isDisplaying = true;
            }
        }        
        
    }

    public void freeParkingSlots() {//available parking
        
        for (CarPark carPark : carParks)
        {
            System.out.println("Time : " + clock.minutes() + "m     " + carPark.carParkName()+ " :    " + carPark.availableSpaces() + " Spaces left" );
        }
    }
    
}
