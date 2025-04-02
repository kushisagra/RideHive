package com.myUber.myUber.Strategies;

import com.myUber.myUber.Entities.RideRequest;

public interface RideFareCalculationStrategy {
double RIDE_FARE_MULTIPLIER=10;

    double calculateDare(RideRequest rideRequest);

}
