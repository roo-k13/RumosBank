package com.rumos.rumosbank.domain.repositories;

import com.rumos.rumosbank.domain.models.Client;

public class ClientRepository extends AbstractRepository<Client> {

    public final Client getById(Long id) {
        return getEntityManager().find(Client.class, id);
    }

    public final Client getByEmail(String email) {
        return getEntityManager().createNamedQuery("Client.getByEmail", Client.class)
                .setParameter("email", email)
                .getSingleResult();
    }
}
