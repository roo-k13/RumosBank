package com.rumos.rumosbank.domain.models;

import com.rumos.rumosbank.domain.models.cards.CreditCard;
import com.rumos.rumosbank.domain.models.cards.DebitCard;
import com.rumos.rumosbank.domain.models.movements.Deposit;
import com.rumos.rumosbank.domain.models.movements.Movement;
import com.rumos.rumosbank.domain.models.movements.Transfer;
import com.rumos.rumosbank.domain.models.movements.Withdraw;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name = "bankaccounts")
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

    public long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public List<Movement> getMovements() {
        return Stream.of(deposits, withdraws, receivedTransfers, sentTransfers)
                .flatMap(Collection::stream).collect(Collectors.toList());

    }

    public BigDecimal getBalance() {
        BigDecimal balance = BigDecimal.valueOf(0);
        for (Deposit deposit : deposits) { balance = balance.add(deposit.getAmount()); }
        for (Withdraw withdraw: withdraws) { balance = balance.subtract(withdraw.getAmount());}
        for (Transfer transfer : receivedTransfers) { balance = balance.add(transfer.getAmount()); }
        for (Transfer transfer : sentTransfers) { balance = balance.subtract(transfer.getAmount()); }
        return balance;
    }

    public List<DebitCard> getDebitCards() {
        return debitCards;
    }

    public List<CreditCard> getCreditCards() {
        return creditCards;
    }

    public void setNumber(String number) {
        if (!number.matches("[0-9]+")) throw new IllegalArgumentException("The account number must only contain digits");
        if (number.length() != 9) throw new IllegalArgumentException("The account number must be exactly 9 digits long");
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMovements(List<Withdraw> withdraws, List<Deposit> deposits, List<Transfer> receivedTransfers, List<Transfer> sentTransfers) {
        this.withdraws = withdraws;this.deposits = deposits;
        this.receivedTransfers = receivedTransfers;
        this.sentTransfers = sentTransfers;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return name + " Nº: " + number;
    }
}
