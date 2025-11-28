package com.huncozyboy.banking.application.port.out;

import com.huncozyboy.domain.RegisteredBankAccount;

public interface RegisterBankAccountPort {

	RegisteredBankAccount createRegisteredBankAccount(
		RegisteredBankAccount.MembershipId membershipId,
		RegisteredBankAccount.BankName bankName,
		RegisteredBankAccount.BankAccountNumber bankAccountNumber,
		RegisteredBankAccount.LinkedStatusIsValid linkedStatusIsValid,
		RegisteredBankAccount.AggregateIdentifier aggregateIdentifier
	);
}
