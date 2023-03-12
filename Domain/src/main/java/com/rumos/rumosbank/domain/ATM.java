package com.rumos.rumosbank.domain;

import com.rumos.rumosbank.domain.models.BankAccount;
import com.rumos.rumosbank.domain.models.cards.Card;
import com.rumos.rumosbank.domain.models.movements.Deposit;
import com.rumos.rumosbank.domain.models.movements.Withdraw;
import com.rumos.rumosbank.domain.repositories.CreditCardRepository;
import com.rumos.rumosbank.domain.repositories.DebitCardRepository;
import com.rumos.rumosbank.domain.repositories.DepositRepository;
import com.rumos.rumosbank.domain.repositories.WithdrawRepository;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class ATM {
    public static final ATM instance;

    static {
        instance = new ATM();}

    public static int[] getWithdrawAmounts() {
        return new int[]{10, 20, 40, 80, 100, 200, 400, 600, 800};
    }

    public static int[] getDepositAmounts() {
        return new int[] {25, 50, 100, 250, 500, 1000, 2500, 5000, 10000};
    }

    public static Card authenticate(String cardNumber, String cardPin) {
        DebitCardRepository debitCardRepository = new DebitCardRepository();
        CreditCardRepository creditCardRepository = new CreditCardRepository();
        Optional<Card> card = Stream.of(
                        debitCardRepository.getByNumber(cardNumber),
                        creditCardRepository.getByNumber(cardNumber)
                )
                .filter(Objects::nonNull)
                .filter(c -> c.isPinCorrect(cardPin))
                .findFirst();

        return card.orElse(null);
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

    public void makeDeposit(BankAccount bankAccount, BigDecimal amount) {
        Deposit deposit = new Deposit();
        deposit.setBankAccount(bankAccount);
        deposit.setAmount(amount);
        new DepositRepository().insert(deposit);
    }
}
