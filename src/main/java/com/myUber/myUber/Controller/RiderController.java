package com.myUber.myUber.Controller;


import com.myUber.myUber.DTO.*;
import com.myUber.myUber.service.RiderService;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rider")
@RequiredArgsConstructor
@Secured("ROLE_RIDER")


public class RiderController {

    private final RiderService riderService;
    @PostMapping("/requestRide")
    public ResponseEntity<RideRequestDTO> requestRide(@RequestBody RideRequestDTO rideRequestDTO) {
        return ResponseEntity.ok(riderService.requestRide(rideRequestDTO));
    }

    @PostMapping("/cancelRide/{rideId}")
    public ResponseEntity<RideDTO> cancelRide(@PathVariable Long rideId) {
        return ResponseEntity.ok(riderService.cancelRide(rideId));
    }
    @PostMapping("/rateDriver")
    public ResponseEntity<DriverDTO> rateDRiver(@RequestBody RatingDTO ratingDTO){
        return ResponseEntity.ok(riderService.rateDriver(ratingDTO.getRideID(), ratingDTO.getRating()));

    }


    //authorisation and authentication will be used here
    @GetMapping("/getMyProfile")
    public ResponseEntity<RiderDTO> getMyProfile(){
        return ResponseEntity.ok(riderService.getMyprofile());
    }
@GetMapping("/getMyRides")

public ResponseEntity<Page<RideDTO>> getAllMyRides(@RequestParam (defaultValue = "0")Integer pageOffset,
                                                   @RequestParam(defaultValue = "10",required = false)Integer pageSize) {

    PageRequest pageRequest = PageRequest.of(pageOffset, pageSize,
            Sort.by(Sort.Direction.DESC, "createdTime", "id"));
    return ResponseEntity.ok(riderService.getAllMyRides(pageRequest));
}



}