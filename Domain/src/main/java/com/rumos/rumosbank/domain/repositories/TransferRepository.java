package com.rumos.rumosbank.domain.repositories;

import com.rumos.rumosbank.domain.models.movements.Transfer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

import java.util.List;

public class TransferRepository {
    private final EntityManager entityManager;

    public TransferRepository() {
        var entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public List<Transfer> getSent(long bankAccountId) {
        String query = "SELECT t FROM Transfer t WHERE t.sender.id = :accountId";
        return entityManager
                .createQuery(query, Transfer.class)
                .setParameter("bankaccountId", bankAccountId)
                .getResultList();
    }

    public List<Transfer> getReceived(long bankAccountId) {
        String query = "SELECT t FROM Transfer t WHERE t.receiver.id = :accountId";
        return entityManager
                .createQuery(query, Transfer.class)
                .setParameter("bankaccountId", bankAccountId)
                .getResultList();
    }
}
