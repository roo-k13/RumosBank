package com.rumos.rumosbank.domain.repositories;

import com.rumos.rumosbank.domain.models.cards.DebitCard;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DebitCardRepository {
    private final EntityManager entityManager;
    private final EntityManagerFactory entityManagerFactory;

    public DebitCardRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("default");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public DebitCard get(String number) {
        String query = "SELECT d FROM DebitCard d WHERE d.number = :number";
        return entityManager.createQuery(query, DebitCard.class).setParameter("number", number).getSingleResult();
    }

    public void insert(DebitCard debitCard) {
        entityManager.getTransaction().begin();
        this.entityManager.persist(debitCard);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    public void update(DebitCard debitCard) {
        entityManager.getTransaction().begin();
        entityManager.merge(debitCard);
        entityManager.getTransaction().commit();
    }
}
