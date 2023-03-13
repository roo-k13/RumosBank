package com.rumos.rumosbank.domain;

import com.rumos.rumosbank.domain.models.BankAccount;
import com.rumos.rumosbank.domain.models.Client;
import com.rumos.rumosbank.domain.models.cards.Card;
import com.rumos.rumosbank.domain.models.cards.CreditCard;
import com.rumos.rumosbank.domain.models.cards.DebitCard;
import com.rumos.rumosbank.domain.models.movements.Transfer;
import com.rumos.rumosbank.domain.repositories.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Bank {
    public static final Bank instance;

    static {
        instance = new Bank();
    }

    private void registerBankAccount(Client client) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setNumber(new Generators().generateRandomNumber(9));
        bankAccount.setName("Conta Corrente");
        bankAccount.setClient(client);
        new BankAccountRepository().insert(bankAccount);
        registerDebitCard(bankAccount);
    }

    private static void setCardProperties(Card card, BankAccount bankAccount) {
        card.setNumber(new Generators().generateRandomNumber(9));
        card.setPin(new Generators().generateRandomNumber(4));
        card.setExpirationDate(LocalDate.now().plusYears(5));
        card.setPinBeenChanged(false);
        card.setBankAccount(bankAccount);
    }

    public static void registerDebitCard(BankAccount bankAccount) {
        DebitCard debitCard = new DebitCard();
        setCardProperties(debitCard, bankAccount);
        new DebitCardRepository().insert(debitCard);
    }

    public static void registerCreditCard(BankAccount bankAccount) {
        CreditCard creditCard = new CreditCard();
        setCardProperties(creditCard, bankAccount);
        new CreditCardRepository().insert(creditCard);
    }

    public static Client authenticate(String email, String password) {
        Client client = new ClientRepository().getByEmail(email);
        return null != client && client.isPasswordCorrect(password) ? client : null;
    }

    public final void registerClient(Client client) {
        new ClientRepository().insert(client);
        registerBankAccount(client);
    }

    public static void updateClient(Client client) {
        new ClientRepository().update(client);
    }

    public static void updateMovements(BankAccount bankAccount) {
        long bankAccountId = bankAccount.getId();
        bankAccount.setMovements(new WithdrawRepository().get(bankAccountId),
                new DepositRepository().get(bankAccountId),
                new TransferRepository().getSent(bankAccountId),
                new TransferRepository().getReceived(bankAccountId));
    }

    public void makeTransfer(BankAccount sender, String receiver, BigDecimal amount) {
        Transfer transfer = new Transfer();
        transfer.setAmount(amount);
        transfer.setSender(sender);
        transfer.setReceiver(new BankAccountRepository().get(receiver));
        new TransferRepository().insert(transfer);
    }
}
