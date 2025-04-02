package com.myUber.myUber.service;

import com.myUber.myUber.Entities.RideRequest;

public interface RideRequestService {

    RideRequest findRequestById(Long rideRequestId);


    void update(RideRequest rideRequest);
}
