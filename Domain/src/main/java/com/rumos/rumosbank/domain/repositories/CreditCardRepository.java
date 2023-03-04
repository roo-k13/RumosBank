package com.rumos.rumosbank.domain.repositories;

import com.rumos.rumosbank.domain.models.cards.CreditCard;

public class CreditCardRepository extends Repository {

    public CreditCard get(String number) {
        String query = "SELECT c FROM CreditCard c WHERE c.number = :number";
        return entityManager.createQuery(query, CreditCard.class).setParameter("number", number).getSingleResult();
    }

    public void insert(CreditCard creditCard) {
        entityManager.getTransaction().begin();
        this.entityManager.persist(creditCard);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }

    public void update(CreditCard creditCard) {
        entityManager.getTransaction().begin();
        entityManager.merge(creditCard);
        entityManager.getTransaction().commit();
    }
}
