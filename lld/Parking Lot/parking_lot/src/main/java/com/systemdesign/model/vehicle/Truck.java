package com.systemdesign.model.vehicle;

import com.systemdesign.model.spot.LargeSpot;
import com.systemdesign.model.spot.ParkingSpot;

import java.util.List;

public class Truck extends Vehicle {

    public Truck(String number) {
        super(number);
    }

    @Override
    public List<Class<? extends ParkingSpot>> getAllowedSpots() {
        return List.of(
                LargeSpot.class
        );
    }
}
