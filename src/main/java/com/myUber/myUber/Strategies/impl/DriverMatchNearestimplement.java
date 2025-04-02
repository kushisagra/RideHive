package com.myUber.myUber.Strategies.impl;

import com.myUber.myUber.Entities.Driver;
import com.myUber.myUber.Entities.RideRequest;
import com.myUber.myUber.Repositories.DriverRepo;
import com.myUber.myUber.Strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Primary

public class DriverMatchNearestimplement implements DriverMatchingStrategy {
private final DriverRepo driverRepo;
    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {

        return driverRepo.findTenNearestDriver(rideRequest.getPickupLocation());
    }
}
