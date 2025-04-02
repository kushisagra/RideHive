package com.myUber.myUber.Strategies.impl;

import com.myUber.myUber.Entities.RideRequest;
import com.myUber.myUber.Strategies.RideFareCalculationStrategy;
import com.myUber.myUber.service.DistanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@RequiredArgsConstructor
public class RideFareDeafultCal implements RideFareCalculationStrategy {
    private final DistanceService distanceService;
    @Override
    public double calculateDare(RideRequest rideRequest) {

        double distance=distanceService.calculateDistance(rideRequest.getPickupLocation(),
                    rideRequest.getDropOffLocation());

        return distance*RIDE_FARE_MULTIPLIER;




    }
}
