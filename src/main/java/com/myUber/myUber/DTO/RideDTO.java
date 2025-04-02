package com.myUber.myUber.DTO;

import com.myUber.myUber.Entities.Driver;
import com.myUber.myUber.Entities.Enums.PaymentMethod;
import com.myUber.myUber.Entities.Enums.RideRequestStatus;
import com.myUber.myUber.Entities.Enums.RideStatus;
import com.myUber.myUber.Entities.Rider;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;
@Data

public class RideDTO {

    private Long id;


    private PointDTO pickupLocation;

    private PointDTO dropOffLocation;


    private LocalDateTime createdTime;


    private RiderDTO rider;

    private DriverDTO driver;
    private String otp;
private double fare;
private RideStatus rideStatus;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    private PaymentMethod paymentMethod;


}
