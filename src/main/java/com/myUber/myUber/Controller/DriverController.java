package com.myUber.myUber.Controller;

import com.myUber.myUber.DTO.*;
import com.myUber.myUber.Entities.Rider;
import com.myUber.myUber.service.DriverService;
import com.myUber.myUber.service.RideService;
import com.myUber.myUber.service.RiderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
@Secured("ROLE_DRIVER")

public class DriverController {

    private final DriverService driverService;
    @PostMapping("/acceptRide/{rideRequestId}")
    public ResponseEntity<RideDTO> acceptRide(@PathVariable Long rideRequestId) {

        return ResponseEntity.ok(driverService.acceptRide(rideRequestId));
    }

    @PostMapping("/startRide/{rideRequestId}")
    public ResponseEntity<RideDTO> startRide(@PathVariable Long rideRequestId,
                                             @RequestBody RideStartDTO rideStartDTO) {

        return ResponseEntity.ok(driverService.startRide(rideRequestId, rideStartDTO.getOtp()));
    }

    @PostMapping("/endRide/{rideRequestId}")
    public ResponseEntity<RideDTO> startRide(@PathVariable Long rideRequestId) {

        return ResponseEntity.ok(driverService.endRide(rideRequestId));
    }
    @PostMapping("/cancelRide/{rideId}")
    public ResponseEntity<RideDTO> cancelRide(@PathVariable Long rideId) {
        return ResponseEntity.ok(driverService.cancelRide(rideId));
    }
    @PostMapping("/rateRider")
    public ResponseEntity<RiderDTO> rateRider(@RequestBody RatingDTO ratingDTO){
        return ResponseEntity.ok(driverService.rateRider(ratingDTO.getRideID(),ratingDTO.getRating()));

    }


    //authorisation and authentication will be used here
    @GetMapping("/getMyProfile")
    public ResponseEntity<DriverDTO> getMyProfile(){

        return ResponseEntity.ok(driverService.getMyprofile());
    }
    @GetMapping("/getMyRides")

    public ResponseEntity<Page<RideDTO>> getAllMyRides(@RequestParam (defaultValue = "0")Integer pageOffset,
                                                       @RequestParam(defaultValue = "10",required = false)Integer pageSize){

        PageRequest pageRequest=PageRequest.of(pageOffset,pageSize,
                Sort.by(Sort.Direction.DESC,"createdTime","id"));
        return ResponseEntity.ok(driverService.getAllMyRides(pageRequest));


    }

}
