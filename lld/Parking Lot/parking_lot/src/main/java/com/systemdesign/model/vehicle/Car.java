package com.systemdesign.model.vehicle;

import com.systemdesign.model.spot.LargeSpot;
import com.systemdesign.model.spot.MediumSpot;
import com.systemdesign.model.spot.ParkingSpot;

import java.util.List;

public class Car extends Vehicle {

    public Car(String number) {
        super(number);
    }

    @Override
    public List<Class<? extends ParkingSpot>> getAllowedSpots() {
        return List.of(
                MediumSpot.class,
                LargeSpot.class
        );
    }
}