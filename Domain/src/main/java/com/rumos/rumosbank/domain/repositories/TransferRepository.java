package com.rumos.rumosbank.domain.repositories;

import com.rumos.rumosbank.domain.models.movements.Transfer;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import javax.naming.OperationNotSupportedException;
import java.util.List;

public class TransferRepository {
    private final EntityManager entityManager;
    private final EntityManagerFactory entityManagerFactory;

    public TransferRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public List<Transfer> getSent(long bankAccountId) throws OperationNotSupportedException {
        String query = "SELECT t FROM Transfer t WHERE t.sender.id = :accountId";
        return entityManager
                .createQuery(query, Transfer.class)
                .setParameter("bankaccountId", bankAccountId)
                .getResultList();
    }

    public List<Transfer> getReceived(long bankAccountId) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }
}
