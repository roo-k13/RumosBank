package com.rumos.rumosbank.domain.repositories;

import com.rumos.rumosbank.domain.models.Client;
import com.rumos.rumosbank.domain.repositories.ClientRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
        Client koen = new Client();
        koen.setFirstName("Koen");
        koen.setLastName("Heene");
        koen.setBirthdate(LocalDate.of(1990, 5, 3));
        koen.setEmailAddress("koenheene3@rumos.pt");
        koen.setPassword("12345");
        new ClientRepository().insert(koen);
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