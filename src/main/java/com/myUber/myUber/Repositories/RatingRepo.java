package com.myUber.myUber.Repositories;

import com.myUber.myUber.Entities.Driver;
import com.myUber.myUber.Entities.Rating;
import com.myUber.myUber.Entities.Ride;
import com.myUber.myUber.Entities.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Repository
public  interface RatingRepo extends JpaRepository<Rating,Long> {



    List<Rating> findByRider(Rider rider);
List<Rating> findByDriver(Driver driver);
    Optional<Rating> findByRide(Ride ride);

}
