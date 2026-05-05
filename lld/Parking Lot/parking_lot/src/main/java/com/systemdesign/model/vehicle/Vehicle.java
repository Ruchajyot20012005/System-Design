package com.systemdesign.model.vehicle;

import com.systemdesign.model.spot.ParkingSpot;

import java.util.List;

public abstract class Vehicle {

    private final String vehicleNumber;

    public Vehicle(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getNumber() {
        return vehicleNumber;
    }

    public abstract List<Class<? extends ParkingSpot>> getAllowedSpots();
}
