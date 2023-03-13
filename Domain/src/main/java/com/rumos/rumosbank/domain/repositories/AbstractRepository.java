package com.rumos.rumosbank.domain.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class AbstractRepository<T> {
    private final EntityManager entityManager;
    private final EntityManagerFactory entityManagerFactory;

    protected final EntityManager getEntityManager() {
        return entityManager;
    }

    protected AbstractRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public final void insert(T object) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(object);
            entityManager.getTransaction().commit();
        } catch (RuntimeException exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            close();
        }
    }

    public final void update(T object) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(object);
            entityManager.getTransaction().commit();
        } catch (RuntimeException exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        } finally {
            close();
        }
    }

    public final void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}