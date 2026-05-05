package com.systemdesign.model.vehicle;

import com.systemdesign.model.spot.LargeSpot;
import com.systemdesign.model.spot.MediumSpot;
import com.systemdesign.model.spot.ParkingSpot;
import com.systemdesign.model.spot.SmallSpot;

import java.util.List;

public class Bike extends Vehicle {

    public Bike(String number) {
        super(number);
    }

    @Override
    public List<Class<? extends ParkingSpot>> getAllowedSpots() {
        return List.of(
                SmallSpot.class,
                MediumSpot.class,
                LargeSpot.class
        );
    }
}
