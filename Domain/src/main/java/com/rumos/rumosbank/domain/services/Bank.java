package com.rumos.rumosbank.domain.services;

import com.rumos.rumosbank.domain.models.BankAccount;
import com.rumos.rumosbank.domain.models.Client;
import com.rumos.rumosbank.domain.repositories.ClientRepository;
import com.rumos.rumosbank.domain.repositories.DepositRepository;
import com.rumos.rumosbank.domain.repositories.TransferRepository;
import com.rumos.rumosbank.domain.repositories.WithdrawRepository;

public class Bank {
    public static final Bank instance;
    static { instance = new Bank(); }


    /* ------------------------------------------------------------ Authentication ------------------------------------------------------------ */

    public Client authenticate(String email, String password) {
        Client client = new ClientRepository().get(email);
        if (client != null && client.isPasswordCorrect(password)) {
            return client;
        }
        return null;
    }

    /* ------------------------------------------------------------ Clients ------------------------------------------------------------ */

    public void registerNewClient(Client client) {
        new ClientRepository().insert(client);
    }

    public void updateClient(Client client) {
        new ClientRepository().update(client);
    }

    /* ------------------------------------------------------------ Movements ------------------------------------------------------------ */

    public void updateMovements(BankAccount bankAccount) {
        long bankAccountId = bankAccount.getId();
        bankAccount.setMovements(new WithdrawRepository().get(bankAccountId),
                                 new DepositRepository().get(bankAccountId),
                                 new TransferRepository().getSent(bankAccountId),
                                 new TransferRepository().getReceived(bankAccountId));
    }
}
