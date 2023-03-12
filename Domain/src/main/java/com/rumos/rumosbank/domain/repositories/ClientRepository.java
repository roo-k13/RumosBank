package com.rumos.rumosbank.domain.repositories;

import com.rumos.rumosbank.domain.models.Client;

public class ClientRepository extends AbstractRepository<Client> {

    public Client getByEmail(Long id) {
        return getEntityManager().find(Client.class, id);
    }

    public Client getByEmail(String email) {
        return getEntityManager().createNamedQuery("Client.getByEmail", Client.class)
                .setParameter("email", email)
                .getSingleResult();
    }
}
