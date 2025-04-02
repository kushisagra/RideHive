package com.myUber.myUber.service;

import com.myUber.myUber.DTO.DriverDTO;
import com.myUber.myUber.DTO.RideDTO;
import com.myUber.myUber.DTO.RideRequestDTO;
import com.myUber.myUber.DTO.RiderDTO;
import com.myUber.myUber.Entities.Rider;
import com.myUber.myUber.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface RiderService {

    RideRequestDTO requestRide(RideRequestDTO rideRequestDTO);

    RideDTO cancelRide(Long rideId);




    DriverDTO rateDriver(Long rideId,Integer rating);

    RiderDTO getMyprofile();
    Page<RideDTO> getAllMyRides(PageRequest pageRequest);
    Rider createNewRider(User user);
    Rider getCurrentRider();





}
