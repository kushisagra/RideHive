package com.myUber.myUber.service.impl;

import com.myUber.myUber.DTO.RideRequestDTO;
import com.myUber.myUber.Entities.Driver;
import com.myUber.myUber.Entities.Enums.RideRequestStatus;
import com.myUber.myUber.Entities.Enums.RideStatus;
import com.myUber.myUber.Entities.Ride;
import com.myUber.myUber.Entities.RideRequest;
import com.myUber.myUber.Entities.Rider;
import com.myUber.myUber.Repositories.RideRepos;
import com.myUber.myUber.exception.ResourceNotFoundException;
import com.myUber.myUber.service.RideRequestService;
import com.myUber.myUber.service.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class RideServiceImplement implements RideService {
    private final RideRepos rideRepos;
    private final RideRequestService rideRequestService;
    private final ModelMapper modelMapper;

    @Override
    public Ride getRideByid(Long rideId) {

        return rideRepos.findById(rideId)
                .orElseThrow(()->new ResourceNotFoundException("ride not found"));
    }



    @Override
    public Ride createNewRide(RideRequest rideRequest, Driver driver) {
        rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);
        Ride ride=modelMapper.map(rideRequest,Ride.class);
        ride.setRideStatus(RideStatus.CONFIRMED);
        ride.setDriver(driver);
        ride.setOTP(genOTP());
        ride.setId(null);
        rideRequestService.update(rideRequest);
      return rideRepos.save(ride);

    }

    @Override
    public Ride updateRideStatus(Ride ride, RideStatus rideStatus) {
        ride.setRideStatus(rideStatus);
        return rideRepos.save(ride);
    }

    @Override
    public Page<Ride> getAllRidesofRider(Rider rider, PageRequest pageRequest) {

        return rideRepos.findByRider(rider,pageRequest);
    }

    @Override
    public Page<Ride> getAllridesOfDriver(Driver driver, PageRequest pageRequest) {

        return rideRepos.findByDriver(driver,pageRequest);
    }
    private String genOTP(){
        Random random=new Random();
        int otpInt=random.nextInt(10000);
        return String.format("%04d",otpInt);
    }
}
