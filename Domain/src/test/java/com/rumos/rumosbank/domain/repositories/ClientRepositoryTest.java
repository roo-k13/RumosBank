package com.rumos.rumosbank.domain.repositories;

import com.rumos.rumosbank.domain.models.Client;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

class ClientRepositoryTest {

    @Test
    public void getByEmail() {
        if (new ClientRepository().get("quimbarreiros@hotmail.com") == null) {
            fail("Unable to get client"); }
    }

    @Test
    void get() {
        if (new ClientRepository().get(1L) == null) {
            fail("Unable to get client"); }
    }

    @Test
    void insert() {
        Client ronaldo = new Client();
        ronaldo.setFirstName("Cristiano");
        ronaldo.setLastName("Ronaldo");
        ronaldo.setBirthdate(LocalDate.of(1985, 2, 5));
        ronaldo.setNif("265478987");
        ronaldo.setEmailAddress("cristianoronaldo@hotmail.pt");
        ronaldo.setPassword("12345");
        new ClientRepository().insert(ronaldo);
    }

    @Test
    void update() {
        Client client = new ClientRepository().get(1L);
        client.setFirstName("Joaquim");
        client.setLastName("Barreiros");
        client.setBirthdate(LocalDate.of(1990, 2, 2));
        client.setEmailAddress("quimbarreiros@hotmail.com");
        client.setPassword("pimba");
        new ClientRepository().update(client);
    }
}