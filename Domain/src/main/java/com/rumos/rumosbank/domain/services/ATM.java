package com.rumos.rumosbank.domain.services;

import com.rumos.rumosbank.domain.models.BankAccount;
import com.rumos.rumosbank.domain.models.cards.Card;
import com.rumos.rumosbank.domain.models.movements.Deposit;
import com.rumos.rumosbank.domain.models.movements.Withdraw;
import com.rumos.rumosbank.domain.repositories.CreditCardRepository;
import com.rumos.rumosbank.domain.repositories.DebitCardRepository;
import com.rumos.rumosbank.domain.repositories.DepositRepository;
import com.rumos.rumosbank.domain.repositories.WithdrawRepository;
import jakarta.persistence.NoResultException;

import java.math.BigDecimal;

public class ATM {
    private static final int[] withdrawAmounts;
    private static final int[] depositAmounts;
    public static final ATM instance;

    static {
        withdrawAmounts = new int[] {10, 20, 40, 80, 100, 200, 400, 600, 800};
        depositAmounts = new int[] {25, 50, 100, 250, 500, 1000, 2500, 5000, 10000};
        instance = new ATM(); }

    public Card authenticate(String cardNumber, String cardPin) {
        Card card;
        try {
            card = new DebitCardRepository().get(cardNumber);
            if (card.isPinCorrect(cardPin)) return card;
        }  catch (NoResultException exception) { System.out.println(exception.getMessage()); }
        try {
            card = new CreditCardRepository().get(cardNumber);
            if (card.isPinCorrect(cardPin)) return card;
        }  catch (NoResultException exception) { System.out.println(exception.getMessage()); }
        return null;
    }

    /* ------------------------------------------------------------ Withdraw ------------------------------------------------------------ */


    public int[] getWithdrawAmounts() {
        return withdrawAmounts;
    }

    public void makeWithdraw(BankAccount bankAccount, BigDecimal amount) throws IllegalArgumentException {
        if (amount.compareTo(bankAccount.getBalance()) > 0) {
            throw new IllegalArgumentException("The withdraw amount can't be bigger than the account balance");
        }

        Withdraw withdraw = new Withdraw();
        withdraw.setBankAccount(bankAccount);
        withdraw.setAmount(amount);
        new WithdrawRepository().insert(withdraw);
    }

    /* ------------------------------------------------------------ Deposit ------------------------------------------------------------ */


    public int[] getDepositAmounts() {
        return depositAmounts;
    }

    public void makeDeposit(BankAccount bankAccount, BigDecimal amount) {
        Deposit deposit = new Deposit();
        deposit.setBankAccount(bankAccount);
        deposit.setAmount(amount);
        new DepositRepository().insert(deposit);
    }
}
