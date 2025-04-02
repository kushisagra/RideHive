package com.myUber.myUber.Strategies;

import com.myUber.myUber.Entities.Driver;
import com.myUber.myUber.Entities.RideRequest;

import java.util.List;

public interface DriverMatchingStrategy {

     List<Driver> findMatchingDriver(RideRequest rideRequest);
}
