package com.myUber.myUber.Repositories;

import com.myUber.myUber.Entities.WalletTransaction;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletTransactionRepo extends JpaRepository<WalletTransaction,Long> {


}
