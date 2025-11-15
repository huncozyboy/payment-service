package com.huncozyboy.common.saga.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckRegisteredBankAccountCommand {

	@TargetAggregateIdentifier
	private String aggregateIdentifier;
	private String rechargingRequestId;
	private String CheckRegisteredBankAccountId;
	private String membershipId;
	private String bankName;
	private String bankAccountNumber;
	private int amount;
}
