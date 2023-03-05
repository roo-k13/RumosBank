package com.rumos.rumosbank.domain.models.movements;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

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

    public void setAmount(int amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("The amount of the movement can't be lower or equal to zero");
        this.amount = new BigDecimal(amount);
    }


    public BigDecimal getAmount() {
        return amount;
    }

    /* ------------------------------------------------------------ Date ------------------------------------------------------------ */

    public String getLongDate() { return timestamp.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)); }

    /* ------------------------------------------------------------ ToString ------------------------------------------------------------ */

    @Override
    public String toString() { return getClass().getSimpleName() + ": " + amount + "€ - " + getLongDate(); }
}