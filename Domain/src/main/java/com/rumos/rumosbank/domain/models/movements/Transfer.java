package com.rumos.rumosbank.domain.models.movements;

import com.rumos.rumosbank.domain.models.BankAccount;
import jakarta.persistence.*;

@Entity
@Table(name = "transfers")
public class Transfer extends Movement {
    @ManyToOne
    private BankAccount sender;
    @ManyToOne
    private BankAccount receiver;
}
