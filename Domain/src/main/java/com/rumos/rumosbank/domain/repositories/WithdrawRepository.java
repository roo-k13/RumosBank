package com.rumos.rumosbank.domain.repositories;

import com.rumos.rumosbank.domain.models.movements.Withdraw;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class WithdrawRepository {
    private final EntityManager entityManager;
    private final EntityManagerFactory entityManagerFactory;

    public WithdrawRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void insert(Withdraw withdraw) {
        entityManager.getTransaction().begin();
        this.entityManager.persist(withdraw);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    public List<Withdraw> get(long bankAccountId) {
        String query = "SELECT w FROM Withdraw w WHERE w.bankAccount.id = :accountId";
        return entityManager
                .createQuery(query, Withdraw.class)
                .setParameter("accountId", bankAccountId)
                .getResultList();
    }
}
