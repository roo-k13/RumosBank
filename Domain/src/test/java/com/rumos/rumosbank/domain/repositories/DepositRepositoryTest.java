package com.rumos.rumosbank.domain.repositories;

import org.junit.jupiter.api.Test;

class DepositRepositoryTest {

    @Test
    void get() { new DepositRepository().get(1); }
}