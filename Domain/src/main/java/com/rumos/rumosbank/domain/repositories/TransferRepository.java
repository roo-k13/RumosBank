package com.rumos.rumosbank.domain.repositories;

import com.rumos.rumosbank.domain.models.movements.Transfer;

import javax.naming.OperationNotSupportedException;
import java.util.List;

public class TransferRepository {
    public List<Transfer> getSent(long bankAccountId) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    public List<Transfer> getReceived(long bankAccountId) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }
}
