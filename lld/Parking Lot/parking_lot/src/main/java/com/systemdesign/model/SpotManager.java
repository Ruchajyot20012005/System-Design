package com.systemdesign.model;

import com.systemdesign.model.spot.ParkingSpot;
import com.systemdesign.model.vehicle.Vehicle;
import com.systemdesign.strategy.allocationStrategy.SpotAllocationStrategy;

import java.util.List;

public class SpotManager {

    private final SpotAllocationStrategy strategy;

    public SpotManager(SpotAllocationStrategy strategy) {
        this.strategy = strategy;
    }

    public ParkingSpot allocate(Vehicle v, List<ParkingFloor> floors) {
        return strategy.findSpot(v, floors);
    }
}
