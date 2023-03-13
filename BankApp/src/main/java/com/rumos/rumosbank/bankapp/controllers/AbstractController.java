package com.rumos.rumosbank.bankapp.controllers;

import com.rumos.rumosbank.domain.Bank;
import com.rumos.rumosbank.domain.models.Client;

public class AbstractController extends NavigationBarController {
    private static final Bank bank = Bank.instance;
    private Client client;

    public final Client getClient() {
        return client;
    }

    public final void setClient(Client client) {
        this.client = client;
    }

    public static Bank getBank() {
        return bank;
    }
}
