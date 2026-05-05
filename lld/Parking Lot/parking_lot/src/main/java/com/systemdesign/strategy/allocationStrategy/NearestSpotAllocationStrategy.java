package com.systemdesign.strategy.allocationStrategy;

import com.systemdesign.model.ParkingFloor;
import com.systemdesign.model.spot.ParkingSpot;
import com.systemdesign.model.vehicle.Vehicle;

import java.util.List;
import java.util.Queue;

public class NearestSpotAllocationStrategy implements SpotAllocationStrategy{

    @Override
    public ParkingSpot findSpot(Vehicle vehicle, List<ParkingFloor> floors) {
        for (ParkingFloor floor : floors) {
            for (Class<? extends ParkingSpot> clazz : vehicle.getAllowedSpots()) {

                Queue<ParkingSpot> queue = floor.getSpots(clazz);

                ParkingSpot spot = queue.poll();

                if (spot != null && spot.occupySpot(vehicle)) {
                    return spot;
                }
            }
        }
        return null;
    }
}
