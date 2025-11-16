package com.huncozyboy.money.application.port.out;

public interface GetRegisteredBankAccountPort {

	RegisteredBankAccountAggregateIdentifier getRegisteredBankAccount(String membershipId);
}
