package com.myUber.myUber.service.impl;

import com.myUber.myUber.Entities.RideRequest;
import com.myUber.myUber.Repositories.RideRequestRepo;
import com.myUber.myUber.exception.ResourceNotFoundException;
import com.myUber.myUber.service.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImplemen implements RideRequestService {
    private final RideRequestRepo rideRequestRepo;
    @Override
    public RideRequest findRequestById(Long rideRequestId) {
        return rideRequestRepo.findById(rideRequestId).orElseThrow(()->new ResourceNotFoundException("RideRequest not found with id"+rideRequestId));

    }

    @Override
    public void update(RideRequest rideRequest) {
        rideRequestRepo.findById(rideRequest.getId()).orElseThrow(()-> new ResourceNotFoundException("Ride request not found with"+rideRequest.getId()));
        rideRequestRepo.save(rideRequest);

    }
}
