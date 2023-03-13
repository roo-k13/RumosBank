package com.rumos.rumosbank.domain.repositories;

import com.rumos.rumosbank.domain.models.Client;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClientRepositoryTest {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private ClientRepository repository;

    @BeforeEach
    final void setUp() {
        entityManagerFactory = Persistence.createEntityManagerFactory("test");
        entityManager = entityManagerFactory.createEntityManager();
        repository = new ClientRepository();
    }

    @AfterEach
    final void tearDown() {
        if (null != entityManager && entityManager.isOpen()) {
            entityManager.close();
        }
        if (null != entityManagerFactory && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }

    @Test
    final void getById() {
        // given
        Client client = new Client();
        client.setFirstName("John");
        client.setLastName("Doe");
        entityManager.persist(client);

        // when
        Client result = repository.getById(client.getId());

        // then
        Assertions.assertNotNull(result, "");
        Assertions.assertEquals(client, result, "");
    }

    @Test
    final void getByEmail() {
        // given
        Client client = new Client();
        client.setFirstName("John");
        client.setLastName("Doe");
        client.setEmailAddress("john.doe@example.com");
        entityManager.persist(client);

        // when
        Client result = repository.getByEmail("john.doe@example.com");

        // then
        Assertions.assertNotNull(result, "");
        Assertions.assertEquals(client, result, "");
    }
}