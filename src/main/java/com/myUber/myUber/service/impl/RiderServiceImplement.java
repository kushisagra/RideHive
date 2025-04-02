package com.myUber.myUber.service.impl;

import com.myUber.myUber.DTO.DriverDTO;
import com.myUber.myUber.DTO.RideDTO;
import com.myUber.myUber.DTO.RideRequestDTO;
import com.myUber.myUber.DTO.RiderDTO;
import com.myUber.myUber.Entities.*;
import com.myUber.myUber.Entities.Enums.RideRequestStatus;
import com.myUber.myUber.Entities.Enums.RideStatus;
import com.myUber.myUber.Repositories.RideRequestRepo;
import com.myUber.myUber.Repositories.RiderRepo;
import com.myUber.myUber.Strategies.impl.StrategyManager;
import com.myUber.myUber.exception.ResourceNotFoundException;
import com.myUber.myUber.service.DriverService;
import com.myUber.myUber.service.RatingService;
import com.myUber.myUber.service.RideService;
import com.myUber.myUber.service.RiderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class RiderServiceImplement implements RiderService {
    private final ModelMapper modelMapper;
    private final RideRequestRepo rideRequestRepo;
    private final RiderRepo riderRepo;
    private final RideService rideService;
    private final StrategyManager strategyManager;
   private final DriverService driverService;
   private final RatingService ratingService;
    @Override
    @Transactional
    public RideRequestDTO requestRide(RideRequestDTO rideRequestDTO) {
        Rider rider=getCurrentRider();
        RideRequest rideRequest=modelMapper.map(rideRequestDTO,RideRequest.class);
        rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
        rideRequest.setRider(rider);

        Double fare= strategyManager.rideFareCalculationStrategy().calculateDare(rideRequest);
        rideRequest.setFare(fare);

        RideRequest savedRideRequest=rideRequestRepo.save(rideRequest);

        List<Driver> drivers=strategyManager.driverMatchingStrategy(rider.getRating()).findMatchingDriver(rideRequest);
        return modelMapper.map(savedRideRequest,RideRequestDTO.class);
    }

    @Override
    public RideDTO cancelRide(Long rideId) {
        Rider rider=getCurrentRider();
        Ride ride=rideService.getRideByid(rideId);

        if(!rider.equals(ride.getRider())){
            throw new RuntimeException("rider does not own this ride"+rideId);

        }
        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("ride cant be cancelled , invalid status"+ride.getRideStatus());


        }
        Ride savedRide=rideService.updateRideStatus(ride,RideStatus.CANCELLED);
        driverService.updateDriverAvailability(ride.getDriver(),true);
        return modelMapper.map(savedRide,RideDTO.class);




    }

    @Override
    public DriverDTO rateDriver(Long rideId, Integer rating) {


        Ride ride=rideService.getRideByid(rideId);

        Rider rider=getCurrentRider();
        if(!rider.equals(ride.getRider())){
            throw new RuntimeException("Driver is not the owner of ride");
        }
        if(!ride.getRideStatus().equals(RideStatus.ENDED)){
            throw new RuntimeException("ride status is not ended it is "+ride.getRideStatus());

        }
        return ratingService.rateDriver(ride,rating);
    }

    @Override
    public RiderDTO getMyprofile() {
        Rider curremtRider=getCurrentRider();
        return modelMapper.map(curremtRider, RiderDTO.class);
    }

    @Override
    public Page<RideDTO> getAllMyRides(PageRequest pageRequest) {
        Rider currentRider=getCurrentRider();

        return rideService.getAllRidesofRider(currentRider,pageRequest).map(
                ride -> modelMapper.map(ride,RideDTO.class));



    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider=Rider
                .builder()
                .user(user)
                .rating(0.0)
                .build();
        return riderRepo.save(rider);

    }

    @Override
    public Rider getCurrentRider() {

      User user= (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      return riderRepo.findByUser(user).orElseThrow(()->new ResourceNotFoundException(
              "rider not dounf with id"+user.getId())
      );
    }
}
