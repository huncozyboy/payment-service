package com.huncozyboy.banking.adapter.axon.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

import lombok.NoArgsConstructor;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import com.huncozyboy.banking.adapter.axon.command.CreateRegisteredBankAccountCommand;
import com.huncozyboy.banking.adapter.axon.event.CreateRegisteredBankAccountEvent;
import com.huncozyboy.application.port.out.GetBankAccountRequest;
import com.huncozyboy.application.port.out.RequestBankAccountInfoPort;
import com.huncozyboy.common.saga.command.CheckRegisteredBankAccountCommand;
import com.huncozyboy.common.saga.event.CheckedRegisteredBankAccountEvent;
import com.huncozyboy.domain.BankAccount;

@Aggregate
@NoArgsConstructor
public class RegisteredBankAccountAggregate {

	@AggregateIdentifier
	private String id;
	private String membershipId;
	private String bankName;
	private String bankAccountNumber;

	@CommandHandler
	public RegisteredBankAccountAggregate(@NotNull CreateRegisteredBankAccountCommand command) {
		// store event
		apply(new CreateRegisteredBankAccountEvent(
			command.getMembershipId(),
			command.getBankName(),
			command.getBankAccountNumber())
		);
	}

	@CommandHandler
	public void handle(@NotNull CheckRegisteredBankAccountCommand command,
		RequestBankAccountInfoPort bankAccountInfoPort) {
		id = command.getAggregateIdentifier();

		BankAccount bankAccount = bankAccountInfoPort.getBankAccountInfo(
			new GetBankAccountRequest(command.getBankName(), command.getBankAccountNumber()));
		String firmBankingUUID = UUID.randomUUID().toString();

		apply(new CheckedRegisteredBankAccountEvent(
			command.getRechargingRequestId(),
			command.getCheckRegisteredBankAccountId(),
			command.getMembershipId(),
			bankAccount.isValid(),
			command.getAmount(),
			firmBankingUUID,
			bankAccount.getBankName(),
			bankAccount.getBankAccountNumber())
		);
	}

	@EventSourcingHandler
	public void on(CreateRegisteredBankAccountEvent event) {
		id = UUID.randomUUID().toString();
		membershipId = event.getMembershipId();
		bankName = event.getBankName();
		bankAccountNumber = event.getBankAccountNumber();
	}
}
