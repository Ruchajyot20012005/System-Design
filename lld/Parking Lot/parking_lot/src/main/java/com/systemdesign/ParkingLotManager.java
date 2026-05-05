package com.systemdesign;

import com.systemdesign.model.ParkingFloor;
import com.systemdesign.model.SpotManager;
import com.systemdesign.model.gate.EntryGate;
import com.systemdesign.model.gate.ExitGate;
import com.systemdesign.model.spot.LargeSpot;
import com.systemdesign.model.spot.MediumSpot;
import com.systemdesign.model.spot.SmallSpot;
import com.systemdesign.model.vehicle.Bike;
import com.systemdesign.model.vehicle.Car;
import com.systemdesign.model.vehicle.Truck;
import com.systemdesign.model.vehicle.Vehicle;
import com.systemdesign.strategy.allocationStrategy.NearestSpotAllocationStrategy;
import com.systemdesign.strategy.pricingStrategy.HourlyPricingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParkingLotManager {
    public static void main(String[] args) {

        List<ParkingFloor> floor = new ArrayList<>();

        for(int i = 0; i < 5; i++){
            floor.add(new ParkingFloor(List.of(
                    new SmallSpot("F"+i+"-S-"+i),
                    new MediumSpot("F"+i+"-M-"+i+10),
                    new LargeSpot("F"+i+"-L-"+i+20))));
        }

        ParkingLot lot = new ParkingLot(floor);

        SpotManager manager = new SpotManager(new NearestSpotAllocationStrategy());
        EntryGate entry = new EntryGate(manager, lot);

        Map<Class<? extends Vehicle>, Double> hourlyRates = Map.of(
                Bike.class, 10.0,
                Car.class, 20.0,
                Truck.class, 50.0
        );

        PricingService pricingService = new PricingService(new HourlyPricingStrategy(hourlyRates));

        ExitGate exit = new ExitGate(pricingService);

        ParkingLotOrchestrator orchestrator = new ParkingLotOrchestrator(entry, exit);

        orchestrator.start();


    }
}