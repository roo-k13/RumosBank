package com.rumos.rumosbank.domain.models.movements;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private BigDecimal amount;
    private LocalDateTime timestamp;

    public Movement() {
        timestamp = LocalDateTime.now();
    }

    /* ------------------------------------------------------------ Type ------------------------------------------------------------ */

    @SuppressWarnings("unused")
    public String getType() {
        return this.getClass().getSimpleName();
    }

    /* ------------------------------------------------------------ Amounts ------------------------------------------------------------ */

    public void setAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("The amount of the movement can't be lower or equal to zero");
        this.amount = amount;
    }


    public BigDecimal getAmount() {
        return amount;
    }

    /* ------------------------------------------------------------ Timestamp ------------------------------------------------------------ */

    @SuppressWarnings("unused")
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /* ------------------------------------------------------------ To String ------------------------------------------------------------ */

    @Override
    public String toString() {
        return getClass().getSimpleName() + ": " + amount + " - " + timestamp;
    }
}