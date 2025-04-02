package com.myUber.myUber.service;

import com.myUber.myUber.DTO.RideRequestDTO;
import com.myUber.myUber.Entities.Driver;
import com.myUber.myUber.Entities.Enums.RideStatus;
import com.myUber.myUber.Entities.Ride;
import com.myUber.myUber.Entities.RideRequest;
import com.myUber.myUber.Entities.Rider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {

    Ride getRideByid(Long rideId);



    Ride createNewRide(RideRequest rideRequest, Driver driver);

    Ride updateRideStatus(Ride ride, RideStatus rideStatus);

    Page<Ride> getAllRidesofRider(Rider rider, PageRequest pageRequest);
    Page<Ride> getAllridesOfDriver(Driver driver,PageRequest pageRequest);








}
