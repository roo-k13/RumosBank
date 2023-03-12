package com.rumos.rumosbank.domain.repositories;

import com.rumos.rumosbank.domain.models.BankAccount;

public class BankAccountRepository extends AbstractRepository<BankAccount> {

    public BankAccount get(String number) {
        String query = "SELECT b FROM BankAccount b WHERE b.number = :number";
        return getEntityManager().createQuery(query, BankAccount.class)
                .setParameter("number", number)
                .getSingleResult();
    }
}