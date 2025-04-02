package com.myUber.myUber.Entities;

import com.myUber.myUber.Entities.Enums.TransactionMEthod;
import com.myUber.myUber.Entities.Enums.TransactionType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Table(indexes = {
        @Index(name="idx_wallet_transaction_wallet",columnList = "wallet_id"),
        @Index(name="idx_wallet_transaction_id",columnList = "ride_id")

})
public class WalletTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private Double amount;
    private TransactionType transactionType;
    private TransactionMEthod transactionMEthod;
    @ManyToOne
    private Ride ride;
    private String transactionId;

    @ManyToOne
    private Wallet wallet;
    @CreationTimestamp
    private LocalDateTime localDateTime;



}
