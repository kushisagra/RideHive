package com.myUber.myUber.Repositories;

import com.myUber.myUber.Entities.Rider;
import com.myUber.myUber.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RiderRepo extends JpaRepository<Rider,Long> {
    Optional<Rider> findByUser(User user);
}
