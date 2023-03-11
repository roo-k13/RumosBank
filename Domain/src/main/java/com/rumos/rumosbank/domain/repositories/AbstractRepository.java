package com.rumos.rumosbank.domain.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public abstract class AbstractRepository<T> {
    protected final EntityManager entityManager;
    protected final EntityManagerFactory entityManagerFactory;

    protected AbstractRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void insert(T object) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        }
    }

    public void update(T object) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(object);
            em.getTransaction().commit();
        }
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}