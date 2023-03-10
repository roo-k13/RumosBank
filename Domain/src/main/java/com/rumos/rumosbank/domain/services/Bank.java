package com.rumos.rumosbank.domain.services;

import com.rumos.rumosbank.domain.models.BankAccount;
import com.rumos.rumosbank.domain.models.Client;
import com.rumos.rumosbank.domain.models.cards.CreditCard;
import com.rumos.rumosbank.domain.models.cards.DebitCard;
import com.rumos.rumosbank.domain.models.movements.Transfer;
import com.rumos.rumosbank.domain.repositories.*;
import com.rumos.rumosbank.domain.shared.Generators;

import java.math.BigDecimal;

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

    public void registerDebitCard(BankAccount bankAccount) {
        DebitCard debitCard = new DebitCard();
        debitCard.setNumber(new Generators().generateRandomNumber(9));
        debitCard.setPin(new Generators().generateRandomNumber(4));
        debitCard.setHasPinBeenChanged(false);
        debitCard.setBankAccount(bankAccount);
        new DebitCardRepository().insert(debitCard);
    }

    public void registerCreditCard(BankAccount bankAccount) {
        CreditCard creditCard = new CreditCard();
        creditCard.setNumber(new Generators().generateRandomNumber(9));
        creditCard.setHasPinBeenChanged(false);
        creditCard.setBankAccount(bankAccount);
        new CreditCardRepository().insert(creditCard);
    }

    public Client authenticate(String email, String password) {
        Client client = new ClientRepository().get(email);
        return client != null && client.isPasswordCorrect(password) ? client : null;
    }

    public void registerClient(Client client) {
        new ClientRepository().insert(client);
        registerBankAccount(client);
    }

    public void updateClient(Client client) {
        new ClientRepository().update(client);
    }

    public void updateMovements(BankAccount bankAccount) {
        long bankAccountId = bankAccount.getId();
        bankAccount.setMovements(new WithdrawRepository().get(bankAccountId),
                                 new DepositRepository().get(bankAccountId),
                                 new TransferRepository().getSent(bankAccountId),
                                 new TransferRepository().getReceived(bankAccountId));
    }

    public void makeTransfer(BankAccount sender, String receiverBankAccountNumber, BigDecimal amount) {
        Transfer transfer = new Transfer();
        transfer.setAmount(amount);
        transfer.setSender(sender);
        transfer.setReceiver(new BankAccountRepository().get(receiverBankAccountNumber));
        new TransferRepository().insert(transfer);
    }
}
