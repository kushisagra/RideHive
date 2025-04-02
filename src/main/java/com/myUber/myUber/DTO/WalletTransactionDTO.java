package com.myUber.myUber.DTO;

import com.myUber.myUber.Entities.Enums.TransactionMEthod;
import com.myUber.myUber.Entities.Enums.TransactionType;
import com.myUber.myUber.Entities.Ride;
import com.myUber.myUber.Entities.Wallet;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
@Data
@Builder
public class WalletTransactionDTO {

    private Long id;
    private Double amount;
    private TransactionType transactionType;
    private TransactionMEthod transactionMEthod;

    private Ride ride;
    private String transactionId;


    private WalletDTO wallet;
    private LocalDateTime localDateTime;



}
