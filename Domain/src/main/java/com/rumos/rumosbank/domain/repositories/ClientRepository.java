package com.rumos.rumosbank.domain.repositories;

import com.rumos.rumosbank.domain.models.Client;

public class ClientRepository extends AbstractRepository<Client> {

    public Client getByEmail(Long id) {
        return entityManager.find(Client.class, id);
    }

    public Client getByEmail(String email) {
        return entityManager.createNamedQuery("Client.getByEmail", Client.class)
                .setParameter("email", email)
                .getSingleResult();
    }
}
