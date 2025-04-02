package com.myUber.myUber.service.impl;

import com.myUber.myUber.DTO.DriverDTO;
import com.myUber.myUber.DTO.RideDTO;
import com.myUber.myUber.DTO.RiderDTO;
import com.myUber.myUber.DTO.UserDTO;
import com.myUber.myUber.Entities.Driver;
import com.myUber.myUber.Entities.Rating;
import com.myUber.myUber.Entities.Ride;
import com.myUber.myUber.Entities.Rider;
import com.myUber.myUber.Repositories.DriverRepo;
import com.myUber.myUber.Repositories.RatingRepo;
import com.myUber.myUber.Repositories.RiderRepo;
import com.myUber.myUber.exception.ResourceNotFoundException;
import com.myUber.myUber.service.RatingService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingServiceimpl implements RatingService {

    private final RatingRepo ratingRepo;
    private final DriverRepo driverRepo;
    private final RiderRepo riderRepo;
    private final ModelMapper modelMapper;
    @Override
    public DriverDTO rateDriver(Ride ride,Integer rating) {
        Driver driver=ride.getDriver();
        Rating ratingObj=ratingRepo.findByRide(ride)
                .orElseThrow(()->new ResourceNotFoundException("rating not found for  ride with id"+ride));

        if(ratingObj.getDriverRating()!=null)
            throw new RuntimeException("driver is already rated");
        ratingObj.setDriverRating(rating);
ratingRepo.save(ratingObj);
Double newRating=ratingRepo.findByDriver(driver)
        .stream()
        .mapToDouble(Rating::getDriverRating)
        .average().orElse(0.0);
driver.setRating(newRating);
Driver savedDriver=driverRepo.save(driver);
return modelMapper.map(savedDriver, DriverDTO.class);


    }

    @Override
    public RiderDTO rateRider(Ride ride, Integer rating) {

Rider rider=ride.getRider();
        Rating ratingObj=ratingRepo.findByRide(ride)
                .orElseThrow(()->new ResourceNotFoundException("rating not found for  ride with id"+ride));
        if(ratingObj.getRiderRating()!=null)
            throw new RuntimeException("rider is already rated");
        ratingObj.setRiderRating(rating);
        ratingRepo.save(ratingObj);
        Double newRating=ratingRepo.findByRider(rider)
                .stream()
                .mapToDouble(Rating::getRiderRating)
                .average().orElse(0.0);
        rider.setRating(newRating);
        Rider savedRider= riderRepo.save(rider);
        return modelMapper.map(savedRider, RiderDTO.class);


    }

    @Override
    public void createNewRating(Ride ride) {
        Rating rating= Rating.builder()
                .rider(ride.getRider())
                .driver(ride.getDriver())
                .ride(ride)
                .build();

        ratingRepo.save(rating);


    }
}
