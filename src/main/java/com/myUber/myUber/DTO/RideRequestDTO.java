package com.myUber.myUber.DTO;

import com.myUber.myUber.Entities.Enums.PaymentMethod;
import com.myUber.myUber.Entities.Enums.RideRequestStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class RideRequestDTO {

    private Long id;

    private PointDTO pickupLocation;

    private PointDTO dropOffLocation;
    private PaymentMethod paymentMethod;

    private LocalDateTime requestedTime=LocalDateTime.now();

    private RiderDTO rider;
    private  Double fare;
    private RideRequestStatus rideRequestStatus;

}
