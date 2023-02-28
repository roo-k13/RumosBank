package com.rumos.rumosbank.domain.repositories;

import com.rumos.rumosbank.domain.models.movements.Deposit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DepositRepository {
    private final EntityManager entityManager;
    private final EntityManagerFactory entityManagerFactory;

    public DepositRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void insert(Deposit deposit) {
        entityManager.getTransaction().begin();
        this.entityManager.persist(deposit);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}
