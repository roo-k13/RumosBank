package com.rumos.rumosbank.domain.repositories;

import com.rumos.rumosbank.domain.models.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ClientRepository {
    private final EntityManager entityManager;
    private final EntityManagerFactory entityManagerFactory;

    public ClientRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public Client get(Long id) {
        return entityManager.find(Client.class, id);
    }

    public Client get(String email) {
        String query = "SELECT c FROM Client c WHERE c.emailAddress = :email";
        return entityManager.createQuery(query, Client.class).setParameter("email", email).getSingleResult();
    }

    public void insert(Client client) {
        entityManager.getTransaction().begin();
        this.entityManager.persist(client);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    public void update(Client client) {
        entityManager.getTransaction().begin();
        entityManager.merge(client);
        entityManager.getTransaction().commit();
    }
}
