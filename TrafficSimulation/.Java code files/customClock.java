package com.mycompany.NickWasGridlocked;



import static java.lang.Thread.sleep;

    public class customClock implements Runnable,IcustomClock//clock thread class
    {
        //clock attributes
        private int seconds;
        private int minutes;
        private int numberOfTicks = 0;       
        
        
        public customClock(int seconds, int minutes)
        {
            this.seconds = seconds;
            this.minutes = minutes;
        }

        @Override
        public int ticksCount()
        {
            return numberOfTicks;
        }
        public int seconds()
        {
            return seconds;
        }
        public int minutes()
        {
            return minutes;
        }
        public void tick() 
        {
            
            try 
            {
                sleep(StaticClockVariables.oneSecondSimulationValue);
            } 
            catch (InterruptedException ex) 
            {            
            }            
            
            this.seconds += 1;
            if (this.seconds == StaticClockVariables.minutes) {
                this.minutes += 1;
                this.seconds = 0;
            }  
            numberOfTicks++;
        }
        
        public void run()
        {
            while(this.numberOfTicks <= StaticClockVariables.simulationTime)
            {
                this.tick();                
            }            
        }



    }
