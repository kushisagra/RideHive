package com.myUber.myUber.Repositories;

import com.myUber.myUber.Entities.User;
import com.myUber.myUber.Entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepo extends JpaRepository<Wallet,Long> {
    Optional<Wallet> findByUser(User user);
}
