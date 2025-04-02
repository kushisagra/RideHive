package com.myUber.myUber.service.impl;

import com.myUber.myUber.DTO.DriverDTO;
import com.myUber.myUber.DTO.RideDTO;
import com.myUber.myUber.DTO.RiderDTO;
import com.myUber.myUber.Entities.*;
import com.myUber.myUber.Entities.Enums.RideRequestStatus;
import com.myUber.myUber.Entities.Enums.RideStatus;
import com.myUber.myUber.Repositories.DriverRepo;
import com.myUber.myUber.exception.ResourceNotFoundException;
import com.myUber.myUber.service.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service

@RequiredArgsConstructor
public class DriverServiceImple implements DriverService {
   private final RideService rideService;
    private final DriverRepo driverRepo;
    private final ModelMapper modelMapper;
    private final RideRequestService rideRequestService;
    private final PaymentService paymentService;
    private final RatingService ratingService;
    @Transactional
    @Override
    public RideDTO acceptRide(Long rideRequestId) {

        RideRequest rideRequest=rideRequestService.findRequestById(rideRequestId);
if(!rideRequest.getRideRequestStatus().equals(RideRequestStatus.PENDING)){
    throw new RuntimeException("Ride Request cannot be accepted , status is"+rideRequest.getRideRequestStatus());
}


Driver currentDriver=getCurrentDriver();
if(!currentDriver.getAvailable()){
    throw new RuntimeException("driver not avaialbe");
}

currentDriver.setAvailable(false);
Driver savedDriver=driverRepo.save(currentDriver);
rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);
Ride ride=rideService.createNewRide(rideRequest, savedDriver);

return modelMapper.map(ride,RideDTO.class);
    }

    @Override
    public RideDTO cancelRide(Long rideId) {
        //onlycancel if it has not starteted
        Ride ride = rideService.getRideByid(rideId);
        Driver driver = getCurrentDriver();
if(!driver.equals(ride.getDriver())){
    throw new RuntimeException("driver cant start qas not accepted");
}
if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
    throw new RuntimeException("ride cant be cancelled , invalid status"+ride.getRideStatus());


}
rideService.updateRideStatus(ride,RideStatus.CANCELLED);
updateDriverAvailability(driver,true);
return modelMapper.map(ride,RideDTO.class);

    }

    @Override
    public RideDTO startRide(Long rideId,String otp) {

        Ride ride=rideService.getRideByid(rideId);
        Driver driver=getCurrentDriver();
        if(!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver cant start as he has not accepted");
        }
        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("ride status is not confirmed"+ride.getRideStatus());

        }
        if(!otp.equals(ride.getOTP())){
            throw new RuntimeException("otp not valid");
        }
        ride.setStartedAt(LocalDateTime.now());
       Ride savedRide= rideService.updateRideStatus(ride,RideStatus.ONGOING);


        paymentService.createPAyment(savedRide);
        ratingService.createNewRating(savedRide);

return modelMapper.map(savedRide,RideDTO.class);
    }

    @Transactional
    @Override
    public RideDTO endRide(Long rideId) {
        Ride ride=rideService.getRideByid(rideId);
        Driver driver=getCurrentDriver();
        if(!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver cant start as he has not accepted");
        }
        if(!ride.getRideStatus().equals(RideStatus.ONGOING)){
            throw new RuntimeException("ride status is not  ongoing hence cannot be ended"+ride.getRideStatus());

        }
        ride.setEndedAt(LocalDateTime.now());
        Ride savedRide=rideService.updateRideStatus(ride,RideStatus.ENDED);
updateDriverAvailability(driver,true);
paymentService.processPayment(ride);


return modelMapper.map(ride,RideDTO.class);


    }

    @Override
    public RiderDTO rateRider(Long rideId, Integer rating) {
        Ride ride=rideService.getRideByid(rideId);

        Driver driver=getCurrentDriver();
        if(!driver.equals(ride.getDriver())){
            throw new RuntimeException("Driver is not the owner of ride");
        }
        if(!ride.getRideStatus().equals(RideStatus.ENDED)){
            throw new RuntimeException("ride status is not ended it is "+ride.getRideStatus());

        }
return ratingService.rateRider(ride,rating);


    }

    @Override
    public DriverDTO getMyprofile() {
        Driver currentDriver=getCurrentDriver();
        return modelMapper.map(currentDriver,DriverDTO.class);


    }

    @Override
    public Page<RideDTO> getAllMyRides(PageRequest pageRequest) {
        Driver currentDriver=getCurrentDriver();

        return rideService.getAllridesOfDriver(currentDriver,pageRequest).map(
                ride -> modelMapper.map(ride,RideDTO.class));

    }

    @Override
    public Driver getCurrentDriver() {
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return driverRepo.findByUser(user)
                .orElseThrow(()->new ResourceNotFoundException(
                "rider not dounf with id"+user.getId())
        );
    }

    @Override
    public Driver updateDriverAvailability(Driver driver, boolean availabe) {

        driver.setAvailable(availabe);
        return driverRepo.save(driver);

    }

    @Override
    public Driver createNewDriver(Driver driver) {

        return driverRepo.save(driver);
    }
}
