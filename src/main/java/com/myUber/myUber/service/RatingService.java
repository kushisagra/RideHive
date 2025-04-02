package com.myUber.myUber.service;

import com.myUber.myUber.DTO.DriverDTO;
import com.myUber.myUber.DTO.RideDTO;
import com.myUber.myUber.DTO.RiderDTO;
import com.myUber.myUber.Entities.Driver;
import com.myUber.myUber.Entities.Ride;
import com.myUber.myUber.Entities.Rider;

public interface RatingService {


   DriverDTO rateDriver(Ride ride, Integer rating);
    RiderDTO rateRider(Ride ride, Integer rating);

    void createNewRating(Ride ride);

}
