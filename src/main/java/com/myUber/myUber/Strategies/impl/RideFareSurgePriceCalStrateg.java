package com.myUber.myUber.Strategies.impl;

import com.myUber.myUber.Entities.RideRequest;
import com.myUber.myUber.Strategies.RideFareCalculationStrategy;
import com.myUber.myUber.service.DistanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideFareSurgePriceCalStrateg implements RideFareCalculationStrategy {
 private final DistanceService distanceService;
 private static final double SURGE_FACTOR=4;
    @Override
    public double calculateDare(RideRequest rideRequest) {
        double distance=distanceService.calculateDistance(rideRequest.getPickupLocation(),
                rideRequest.getDropOffLocation());

        return distance*RIDE_FARE_MULTIPLIER*SURGE_FACTOR;
    }
}
