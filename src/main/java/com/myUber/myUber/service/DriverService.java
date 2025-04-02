package com.myUber.myUber.service;

import com.myUber.myUber.DTO.DriverDTO;
import com.myUber.myUber.DTO.RideDTO;
import com.myUber.myUber.DTO.RiderDTO;
import com.myUber.myUber.Entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface DriverService {

    RideDTO acceptRide(Long rideRequestId);

RideDTO cancelRide(Long rideId);

RideDTO startRide(Long rideId,String otp);
RideDTO endRide(Long rideId);


RiderDTO rateRider(Long rideId, Integer rating);

DriverDTO getMyprofile();
 Page<RideDTO> getAllMyRides(PageRequest pageRequest);
Driver getCurrentDriver();
Driver updateDriverAvailability(Driver driver,boolean availabe);

Driver createNewDriver(Driver driver);

}
