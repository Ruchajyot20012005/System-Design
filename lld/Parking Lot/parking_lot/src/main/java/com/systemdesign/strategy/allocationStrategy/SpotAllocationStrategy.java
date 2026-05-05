package com.systemdesign.strategy.allocationStrategy;

import com.systemdesign.model.ParkingFloor;
import com.systemdesign.model.spot.ParkingSpot;
import com.systemdesign.model.vehicle.Vehicle;

import java.util.List;

public interface SpotAllocationStrategy {
    ParkingSpot findSpot(Vehicle vehicle, List<ParkingFloor> floors);
}
