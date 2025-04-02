package com.myUber.myUber.Entities;

import com.myUber.myUber.Entities.Enums.PaymentMethod;
import com.myUber.myUber.Entities.Enums.PaymentStatus;
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
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @OneToOne(fetch = FetchType.LAZY)
    private Ride ride;

    @CreationTimestamp
    private LocalDateTime localDateTime;
    private Double amount;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

}
