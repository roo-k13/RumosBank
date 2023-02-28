package com.rumos.rumosbank.domain.repositories;

import com.rumos.rumosbank.domain.models.movements.Deposit;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

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

    public List<Deposit> get(long bankAccountId) {
        String query = "SELECT d FROM Deposit d WHERE d.bankAccount.id = :accountId";
        return entityManager
                .createQuery(query, Deposit.class)
                .setParameter("accountId", bankAccountId)
                .getResultList();
   }
}