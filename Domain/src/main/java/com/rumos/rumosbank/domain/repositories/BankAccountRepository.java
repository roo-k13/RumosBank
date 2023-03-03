package com.rumos.rumosbank.domain.repositories;

import com.rumos.rumosbank.domain.models.BankAccount;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class BankAccountRepository {
    private final EntityManager entityManager;
    private final EntityManagerFactory entityManagerFactory;

    public BankAccountRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void insert(BankAccount bankAccount) {
        entityManager.getTransaction().begin();
        this.entityManager.persist(bankAccount);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    public BankAccount get(String number) {
        String query = "SELECT b FROM BankAccount b WHERE b.number = :number";
        return entityManager.createQuery(query, BankAccount.class).setParameter("number", number).getSingleResult();
    }
}
