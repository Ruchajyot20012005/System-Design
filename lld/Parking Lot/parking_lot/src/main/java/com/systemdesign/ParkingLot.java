package com.systemdesign;

import com.systemdesign.model.ParkingFloor;

import java.util.List;

public class ParkingLot {

    private final List<ParkingFloor> floors;

    public ParkingLot(List<ParkingFloor> floors) {
        this.floors = floors;
    }

    public List<ParkingFloor> getFloors() {
        return floors;
    }
}
