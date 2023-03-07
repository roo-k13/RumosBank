package com.rumos.rumosbank.domain.models;

import com.rumos.rumosbank.domain.models.cards.CreditCard;
import com.rumos.rumosbank.domain.models.cards.DebitCard;
import com.rumos.rumosbank.domain.models.movements.Deposit;
import com.rumos.rumosbank.domain.models.movements.Movement;
import com.rumos.rumosbank.domain.models.movements.Transfer;
import com.rumos.rumosbank.domain.models.movements.Withdraw;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "bankaccounts")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private List<Deposit> deposits;
    @OneToMany(cascade = CascadeType.ALL)
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

    /* ------------------------------------------------------------ Id ------------------------------------------------------------ */

    public long getId() {
        return id;
    }

    /* ------------------------------------------------------------ Number ------------------------------------------------------------ */

    @SuppressWarnings("unused")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        if (!number.matches("[0-9]+")) throw new IllegalArgumentException("The account number must only contain digits");
        if (number.length() != 9) throw new IllegalArgumentException("The account number must be exactly 9 digits long");
        this.number = number;
    }

    /* ------------------------------------------------------------ Name ------------------------------------------------------------ */

    @SuppressWarnings("unused")
    public String getName() {
        return name;
    }

    /* ------------------------------------------------------------ Balance ------------------------------------------------------------ */

    public BigDecimal getBalance() {
        BigDecimal balance = BigDecimal.valueOf(0);
        for (Deposit deposit : deposits) { balance = balance.add(deposit.getAmount()); }
        for (Withdraw withdraw: withdraws) { balance = balance.subtract(withdraw.getAmount());}
        for (Transfer transfer : receivedTransfers) { balance = balance.add(transfer.getAmount()); }
        for (Transfer transfer : sentTransfers) { balance = balance.subtract(transfer.getAmount()); }
        return balance;
    }

    /* ------------------------------------------------------------ Movements ------------------------------------------------------------ */

    public ArrayList<Movement> getMovements() {
        ArrayList<Movement> movements = new ArrayList<>();
        movements.addAll(deposits);
        movements.addAll(withdraws);
        movements.addAll(receivedTransfers);
        movements.addAll(sentTransfers);
        return movements;
    }

    public void setMovements(List<Withdraw> withdraws, List<Deposit> deposits, List<Transfer> receivedTransfers, List<Transfer> sentTransfers) {
        this.withdraws = withdraws;
        this.deposits = deposits;
        this.receivedTransfers = receivedTransfers;
        this.sentTransfers = sentTransfers;
    }

    /* ------------------------------------------------------------ Cards ------------------------------------------------------------ */

    public List<DebitCard> getDebitCards() { return debitCards; }

    public List<CreditCard> getCreditCards() { return creditCards; }

    /* ------------------------------------------------------------ To String ------------------------------------------------------------ */

    @Override
    public String toString() {
        return name + " Nº: " + number;
    }
}
