package com.huncozyboy.banking.application.port.out;

import com.huncozyboy.domain.BankAccount;

public interface RequestBankAccountInfoPort {

	BankAccount getBankAccountInfo(GetBankAccountRequest request);
}
