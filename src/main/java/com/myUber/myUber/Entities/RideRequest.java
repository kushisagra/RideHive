package com.myUber.myUber.Entities;


import com.myUber.myUber.Entities.Enums.PaymentMethod;
import com.myUber.myUber.Entities.Enums.RideRequestStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter

@Table(
        indexes = {
                @Index(name = "idx_rider",
                columnList = "rider_id")
        }
)

public class RideRequest {
@Id
@GeneratedValue(strategy = GenerationType.SEQUENCE)
private Long id;
    @Column(columnDefinition = "Geometry(Point, 4326)")

private Point pickupLocation;
    @Column(columnDefinition = "Geometry(Point, 4326)")
private Point dropOffLocation;

    @CreationTimestamp
    private LocalDateTime requestTime;

@ManyToOne(fetch = FetchType.LAZY)
    private Rider rider;


@Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

@Enumerated(EnumType.STRING)
    private RideRequestStatus rideRequestStatus;

private Double fare;


private LocalDateTime startedAt;
private LocalDateTime endedAt;

}
