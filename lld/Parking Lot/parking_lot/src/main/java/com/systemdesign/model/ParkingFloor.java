package com.systemdesign.model;

import com.systemdesign.model.spot.ParkingSpot;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ParkingFloor {

    private final Map<Class<? extends ParkingSpot>, Queue<ParkingSpot>> spotMap = new ConcurrentHashMap<>();

    public ParkingFloor(List<ParkingSpot> spots) {
        for (ParkingSpot spot : spots) {
            spotMap
                    .computeIfAbsent(spot.getClass(), k -> new ConcurrentLinkedQueue<>())
                    .offer(spot);
        }
    }

    public Queue<ParkingSpot> getSpots(Class<? extends ParkingSpot> clazz) {
        return spotMap.getOrDefault(clazz, new ConcurrentLinkedQueue<>());
    }
}
