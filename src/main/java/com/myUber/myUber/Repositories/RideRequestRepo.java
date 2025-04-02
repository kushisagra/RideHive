package com.myUber.myUber.Repositories;

import com.myUber.myUber.Entities.RideRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRequestRepo extends JpaRepository<RideRequest,Long> {

}
