package com.myUber.myUber.DTO;

import com.myUber.myUber.Entities.User;
import com.myUber.myUber.Entities.WalletTransaction;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

import java.util.List;
@Data
public class WalletDTO {

    private Long id;

    private UserDTO user;
    private Double Balance;

    private List<WalletTransaction> transactios;
}
