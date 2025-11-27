package com.payment_service.adapter.out;

import com.payment_service.domain.RegisteredBankAccount;

public interface GetRegisteredBankAccountPort {

	RegisteredBankAccount getRegisteredBankAccount(
		GetRegisteredBankAccountCommand command);
}
