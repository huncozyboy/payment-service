package com.huncozyboy.banking.application.port.in;

import com.huncozyboy.domain.RegisteredBankAccount;

public interface GetRegisteredBankAccountUseCase {

	RegisteredBankAccount getRegisteredBankAccount(GetRegisteredBankAccountCommand command);
}
