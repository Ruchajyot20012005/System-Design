package com.systemdesign.model.gate;

import com.systemdesign.ParkingLot;
import com.systemdesign.model.SpotManager;
import com.systemdesign.model.Ticket;
import com.systemdesign.model.spot.ParkingSpot;
import com.systemdesign.model.vehicle.Vehicle;
import com.systemdesign.utility.IdGenerator;

public class EntryGate {

    private final SpotManager manager;
    private final ParkingLot lot;

    public EntryGate(SpotManager manager, ParkingLot lot) {
        this.manager = manager;
        this.lot = lot;
    }

    public Ticket park(Vehicle v) {
        ParkingSpot spot = manager.allocate(v, lot.getFloors());

        if (spot == null) throw new RuntimeException("No Spot available");

        return new Ticket(IdGenerator.generate(), v, spot);
    }
}
