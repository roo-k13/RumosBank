package com.rumos.rumosbank.domain.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public abstract class Repository {
    protected final EntityManager entityManager;
    protected final EntityManagerFactory entityManagerFactory;

    protected Repository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
    }
}
