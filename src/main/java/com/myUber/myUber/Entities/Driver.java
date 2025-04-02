package com.myUber.myUber.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.locationtech.jts.geom.Point;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(indexes = {
        @Index(name="idx_vehicle_id",columnList = "vehicleId")
})
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;


    private Double rating;
    private  String vehicleId;


    private Boolean available;

    @Column(columnDefinition = "Geometry(Point, 4326)")
    Point current_location;


}
