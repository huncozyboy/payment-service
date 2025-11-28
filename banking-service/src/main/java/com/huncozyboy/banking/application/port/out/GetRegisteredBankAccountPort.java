package com.huncozyboy.banking.application.port.out;

import com.huncozyboy.banking.application.port.in.GetRegisteredBankAccountCommand;
import com.huncozyboy.domain.RegisteredBankAccount;

public interface GetRegisteredBankAccountPort {

	RegisteredBankAccount getRegisteredBankAccount(
		GetRegisteredBankAccountCommand command);
}
