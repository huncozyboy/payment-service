package com.huncozyboy.banking.application.port.out;

public record GetBankAccountRequest(
	String bankName,
	String bankAccountNumber
) {

}
