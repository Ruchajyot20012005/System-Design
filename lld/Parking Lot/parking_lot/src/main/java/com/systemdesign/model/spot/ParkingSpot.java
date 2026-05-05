package com.systemdesign.model.spot;

import com.systemdesign.model.vehicle.Vehicle;

import java.util.concurrent.locks.ReentrantLock;

public abstract class ParkingSpot {

    private final String spotId;
    private boolean occupied;
    private Vehicle vehicle;

    private final ReentrantLock lock = new ReentrantLock();

    public ParkingSpot(String spotId) {
        this.spotId = spotId;
    }

    public String getSpotId() {
        return spotId;
    }

    public boolean canFit(Vehicle v) {
        return v.getAllowedSpots().contains(this.getClass());
    }

    public boolean occupySpot(Vehicle v) {
        if (lock.tryLock()) {
            try {
                if (!occupied && canFit(v)) {
                    occupied = true;
                    vehicle = v;
                    return true;
                }
                return false;
            } finally {
                lock.unlock();
            }
        }
        return false;
    }

    public void releaseSpot() {
        lock.lock();
        try {
            occupied = false;
            vehicle = null;
        } finally {
            lock.unlock();
        }
    }
}
