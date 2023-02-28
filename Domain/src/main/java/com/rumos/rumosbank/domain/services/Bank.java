package com.rumos.rumosbank.domain.services;

import com.rumos.rumosbank.domain.models.Client;
import com.rumos.rumosbank.domain.repositories.ClientRepository;

public class Bank {
    public static final Bank instance;
    static { instance = new Bank(); }

    public Client authenticate(String email, String password) {
        Client client = new ClientRepository().get(email);
        if (client != null && client.isPasswordCorrect(password)) {
            return client;
        }
        return null;
    }

    public void registerNewClient(Client client) {
        new ClientRepository().insert(client);
    }

    public void updateClient(Client client) {
        new ClientRepository().update(client);
    }
}
