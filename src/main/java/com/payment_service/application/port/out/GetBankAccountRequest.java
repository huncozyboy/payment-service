package com.payment_service.adapter.out;

public record GetBankAccountRequest(
	String bankName,
	String bankAccountNumber
) {

}
