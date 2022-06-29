package com.mycompany.NickWasGridlocked;

public interface IRoad {
    Vehicle[] getRoads();
    void generateVehicles(Vehicle aVehicle, customClock clock);
    Vehicle removeVehicles();
}
