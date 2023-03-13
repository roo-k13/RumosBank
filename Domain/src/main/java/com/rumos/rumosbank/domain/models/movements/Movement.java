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

    public String getType() {
        return this.getClass().getSimpleName();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(Number amount) {
        if (null == amount || 0 >= amount.doubleValue())
            throw new IllegalArgumentException("The amount of the movement can't be lower or equal to zero");
        if (amount instanceof BigDecimal)
            this.amount = (BigDecimal) amount;
        else
            this.amount = BigDecimal.valueOf(amount.doubleValue());
    }

    public final String getLongDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
        return timestamp.format(formatter);
    }

    @Override
    public final String toString() {
        Class<? extends Movement> aClass = getClass();
        return aClass.getSimpleName() + ": " + amount + "€ - " + getLongDate();
    }
}