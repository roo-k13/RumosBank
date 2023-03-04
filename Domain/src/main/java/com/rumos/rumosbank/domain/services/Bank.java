package com.rumos.rumosbank.domain.services;

import com.rumos.rumosbank.domain.models.BankAccount;
import com.rumos.rumosbank.domain.models.Client;
import com.rumos.rumosbank.domain.models.cards.DebitCard;
import com.rumos.rumosbank.domain.models.movements.Transfer;
import com.rumos.rumosbank.domain.repositories.*;
import com.rumos.rumosbank.domain.shared.Generators;

import java.math.BigDecimal;

public class Bank {
    public static final Bank instance;
    static { instance = new Bank(); }


    /* --------------------------------------------------------- Authentication -------------------------------------------------------- */

    public Client authenticate(String email, String password) {
        Client client = new ClientRepository().get(email);
        return client != null && client.isPasswordCorrect(password) ? client : null;
    }

    /* ------------------------------------------------------------ Clients ------------------------------------------------------------ */

    public void registerNewClient(Client client) { new ClientRepository().insert(client); }

    public void updateClient(Client client) { new ClientRepository().update(client); }

    /* ----------------------------------------------------------- Movements ----------------------------------------------------------- */

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

    /* ------------------------------------------------------------ Cards ------------------------------------------------------------- */

    public void createNewDebitCard(BankAccount bankAccount) {
        DebitCard debitCard = new DebitCard();
        debitCard.setNumber(new Generators().generateRandomNumber(9));
        debitCard.setHasPinBeenChanged(false);
        debitCard.setBankAccount(bankAccount);
        new DebitCardRepository().insert(debitCard);
    }
}
