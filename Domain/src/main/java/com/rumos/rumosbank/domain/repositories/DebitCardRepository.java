package com.rumos.rumosbank.domain.repositories;

import com.rumos.rumosbank.domain.models.cards.DebitCard;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class DebitCardRepository {

    private final EntityManager entityManager;
    private final EntityManagerFactory entityManagerFactory;

    public DebitCardRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public DebitCard getByNumber(String number) {
        String query = "SELECT d FROM DebitCard d WHERE d.number = :number";
        TypedQuery<DebitCard> typedQuery = entityManager.createQuery(query, DebitCard.class);
        typedQuery.setParameter("number", number);
        try {
            return typedQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void insert(DebitCard debitCard) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(debitCard);
            em.getTransaction().commit();
        }
    }

    public void update(DebitCard debitCard) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(debitCard);
            em.getTransaction().commit();
        }
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }

}