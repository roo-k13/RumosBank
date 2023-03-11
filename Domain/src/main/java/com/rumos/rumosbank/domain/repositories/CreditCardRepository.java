package com.rumos.rumosbank.domain.repositories;

import com.rumos.rumosbank.domain.models.cards.CreditCard;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class CreditCardRepository {

    private final EntityManager entityManager;
    private final EntityManagerFactory entityManagerFactory;

    public CreditCardRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public CreditCard getByNumber(String number) {
        String query = "SELECT c FROM CreditCard c WHERE c.number = :number";
        TypedQuery<CreditCard> typedQuery = entityManager.createQuery(query, CreditCard.class);
        typedQuery.setParameter("number", number);
        try {
            return typedQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void insert(CreditCard creditCard) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(creditCard);
            em.getTransaction().commit();
        }
    }

    public void update(CreditCard creditCard) {
        try (EntityManager em = entityManagerFactory.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(creditCard);
            em.getTransaction().commit();
        }
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }

}