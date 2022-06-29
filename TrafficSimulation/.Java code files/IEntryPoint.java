package com.mycompany.NickWasGridlocked;

import java.util.List;

public interface IEntryPoint {

    void createVehicles(Vehicle aVehicle);
    int randomArrival(List<Parking> destination);
}
