package com.rumos.rumosbank.domain.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public abstract class AbstractRepository<T> {
    private final EntityManager entityManager;
    private final EntityManagerFactory entityManagerFactory;

    protected final EntityManager getEntityManager() {
        return entityManager;
    }

    protected AbstractRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void insert(T object) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(object);
            entityManager.getTransaction().commit();
        } catch (Exception exception) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw exception;
        } finally {
            close();
        }
    }

    public final void update(T object) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(object);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw e;
        } finally {
            close();
        }
    }

    public final void close() {
        entityManager.close();
        entityManagerFactory.close();
    }
}