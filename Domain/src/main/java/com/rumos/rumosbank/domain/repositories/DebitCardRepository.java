package com.rumos.rumosbank.domain.repositories;

import com.rumos.rumosbank.domain.models.cards.DebitCard;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class DebitCardRepository extends AbstractRepository<DebitCard> {

    public DebitCard getByNumber(String number) {
        String query = "SELECT d FROM DebitCard d WHERE d.number = :number";
        TypedQuery<DebitCard> typedQuery = getEntityManager().createQuery(query, DebitCard.class);
        typedQuery.setParameter("number", number);
        try {
            return typedQuery.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}