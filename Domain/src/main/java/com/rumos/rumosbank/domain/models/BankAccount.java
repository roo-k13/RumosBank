package com.rumos.rumosbank.domain.models;

import com.rumos.rumosbank.domain.models.cards.Card;
import com.rumos.rumosbank.domain.models.cards.CreditCard;
import com.rumos.rumosbank.domain.models.cards.DebitCard;
import com.rumos.rumosbank.domain.models.movements.Deposit;
import com.rumos.rumosbank.domain.models.movements.Movement;
import com.rumos.rumosbank.domain.models.movements.Transfer;
import com.rumos.rumosbank.domain.models.movements.Withdraw;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "accounts")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String name;
    @OneToMany
    @JoinColumn(name = "account_id")
    private List<Deposit> deposits;
    @OneToMany
    @JoinColumn(name = "account_id")
    private List<Withdraw> withdraws;
    @OneToMany
    @JoinColumn(name = "receiver_id")
    private List<Transfer> receivedTransfers;
    @OneToMany
    @JoinColumn(name = "sender_id")
    private List<Transfer> sentTransfers;
    @OneToMany
    @JoinColumn(name = "account_id")
    private List<DebitCard> debitCards;
    @OneToMany
    @JoinColumn(name = "account_id")
    private List<CreditCard> creditCards;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public final long getId() {
        return id;
    }

    public final String getNumber() {
        return number;
    }

    public final String getName() {
        return name;
    }

    public final List<Movement> getMovements() {
        return Stream.of(deposits, withdraws, receivedTransfers, sentTransfers)
                .flatMap(Collection::stream).collect(Collectors.toList());
    }

    public final BigDecimal getBalance() {
        BigDecimal balance = BigDecimal.valueOf(0);
        for (Deposit deposit : deposits) {
            balance = balance.add(deposit.getAmount());
        }
        for (Withdraw withdraw : withdraws) {
            balance = balance.subtract(withdraw.getAmount());
        }
        for (Transfer transfer : receivedTransfers) {
            balance = balance.add(transfer.getAmount());
        }
        for (Transfer transfer : sentTransfers) {
            balance = balance.subtract(transfer.getAmount());
        }
        return balance;
    }

    public final List<DebitCard> getDebitCards() {
        return Collections.unmodifiableList(debitCards);
    }

    public final List<CreditCard> getCreditCards() {
        return Collections.unmodifiableList(creditCards);
    }

    public final List<Card> getCards() {
        return Stream.of(getDebitCards(), getCreditCards())
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public final void setNumber(String number) {
        if (!number.matches("[0-9]+"))
            throw new IllegalArgumentException("The account number must only contain digits");
        if (number.length() != 9)
            throw new IllegalArgumentException("The account number must be exactly 9 digits long");
        this.number = number;
    }

    public final void setName(String name) {
        this.name = name;
    }

    public final void setMovements(List<Withdraw> withdraws, List<Deposit> deposits,
                                   List<Transfer> receivedTransfers, List<Transfer> sentTransfers) {
        this.withdraws = withdraws;
        this.deposits = deposits;
        this.receivedTransfers = receivedTransfers;
        this.sentTransfers = sentTransfers;
    }

    public final void setClient(Client client) {
        this.client = client;
    }

    @Override
    public final String toString() {
        return name + " Nº: " + number;
    }
}
