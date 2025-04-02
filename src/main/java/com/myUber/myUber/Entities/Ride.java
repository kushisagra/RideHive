package com.myUber.myUber.Entities;

import com.myUber.myUber.Entities.Enums.PaymentMethod;
import com.myUber.myUber.Entities.Enums.RideStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(indexes =  {
        @Index(name="idx_ride_rider",columnList = "rider_id"),
        @Index(name="idx_driver",columnList = "driver_id")
})
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(columnDefinition = "Geometry(Point, 4326)")

    private Point pickupLocation;
    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point dropOffLocation;

    @CreationTimestamp
    private LocalDateTime createdTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private Rider rider;
    @ManyToOne(fetch = FetchType.LAZY)
    private  Driver driver;


    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private RideStatus rideStatus;

    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private Double fare;
    private String OTP;

}
