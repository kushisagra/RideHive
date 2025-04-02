package com.myUber.myUber.Strategies.impl;

import com.myUber.myUber.Entities.Driver;
import com.myUber.myUber.Entities.RideRequest;
import com.myUber.myUber.Repositories.DriverRepo;
import com.myUber.myUber.Strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class DriverMatchRatingimpl implements DriverMatchingStrategy {
    private  final DriverRepo driverRepo;
    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {
        return driverRepo.findTenTopRatedDriver(rideRequest.getPickupLocation());
    }
}
