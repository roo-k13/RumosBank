package com.rumos.rumosbank.domain.repositories;

import com.rumos.rumosbank.domain.models.movements.Transfer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class TransferRepository {
    private final EntityManager entityManager;
    private final EntityManagerFactory entityManagerFactory;

    public TransferRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void insert(Transfer transfer) {
        entityManager.getTransaction().begin();
        this.entityManager.persist(transfer);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    public List<Transfer> getSent(long bankAccountId) {
        String query = "SELECT t FROM Transfer t WHERE t.sender.id = :accountId";
        return entityManager
                .createQuery(query, Transfer.class)
                .setParameter("accountId", bankAccountId)
                .getResultList();
    }

    public List<Transfer> getReceived(long bankAccountId) {
        String query = "SELECT t FROM Transfer t WHERE t.receiver.id = :accountId";
        return entityManager
                .createQuery(query, Transfer.class)
                .setParameter("accountId", bankAccountId)
                .getResultList();
    }
}
