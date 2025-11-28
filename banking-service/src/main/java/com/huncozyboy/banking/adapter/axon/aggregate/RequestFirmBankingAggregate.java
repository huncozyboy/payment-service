package com.huncozyboy.banking.adapter.axon.aggregate;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import com.huncozyboy.banking.adapter.axon.command.CreateRequestFirmBankingCommand;
import com.huncozyboy.banking.adapter.axon.command.UpdateRequestFirmBankingCommand;
import com.huncozyboy.banking.adapter.axon.event.RequestFirmBankingCreatedEvent;
import com.huncozyboy.banking.adapter.axon.event.UpdateRequestFirmBankingEvent;
import com.huncozyboy.application.port.out.RequestExternalFirmBankingPort;
import com.huncozyboy.application.port.out.RequestFirmBankingPort;
import com.huncozyboy.common.saga.command.RequestFirmBankingCommand;
import com.huncozyboy.common.saga.command.RollbackFirmBankingRequestCommand;
import com.huncozyboy.common.saga.event.RequestFirmBankingFinishedEvent;
import com.huncozyboy.common.saga.event.RollbackFirmBankingFinishedEvent;
import com.huncozyboy.domain.ExternalFirmBankingRequest;
import com.huncozyboy.domain.FirmBankingRequest;
import com.huncozyboy.domain.FirmBankingResult;

@Aggregate
@Data
@NoArgsConstructor
public class RequestFirmBankingAggregate {

	@AggregateIdentifier
	private String id;
	private String fromBankName;
	private String fromBankAccountNumber;
	private String toBankName;
	private String toBankAccountNumber;

	private int moneyAmount;
	private int firmBankingStatus;

	@CommandHandler
	public RequestFirmBankingAggregate(@NotNull CreateRequestFirmBankingCommand command) {
		// store event
		apply(new RequestFirmBankingCreatedEvent(command.getFromBankName(),
			command.getFromBankAccountNumber(),
			command.getToBankName(),
			command.getToBankAccountNumber(),
			command.getMoneyAmount()));
	}

	@CommandHandler
	public String handle(@NotNull UpdateRequestFirmBankingCommand command) {
		// store event
		apply(new UpdateRequestFirmBankingEvent(command.getAggregateIdentifier(),
			command.getFirmBankingStatus()));
		return id;
	}

	@CommandHandler
	public RequestFirmBankingAggregate(RequestFirmBankingCommand command,
		RequestFirmBankingPort firmBankingPort,
		RequestExternalFirmBankingPort externalFirmBankingPort) {
		id = command.getAggregateIdentifier();

		firmBankingPort.createFirmBankingRequest(
			new FirmBankingRequest.FromBankName(command.getToBankName()),
			new FirmBankingRequest.FromBankAccountNumber(command.getToBankAccountNumber()),
			new FirmBankingRequest.ToBankName("fastcampus-bank"),
			new FirmBankingRequest.ToBankAccountNumber("123-333-9999"),
			new FirmBankingRequest.MoneyAmount(command.getMoneyAmount()),
			new FirmBankingRequest.FirmBankingStatus(0),
			new FirmBankingRequest.FirmBankingAggregateIdentifier(id));

		// firmBanking!
		FirmBankingResult firmBankingResult = externalFirmBankingPort.requestExternalFirmBanking(
			new ExternalFirmBankingRequest(
				command.getFromBankName(),
				command.getFromBankAccountNumber(),
				command.getToBankName(),
				command.getToBankAccountNumber(),
				command.getMoneyAmount()
			));

		// 0. 성공, 1. 실패
		int resultCode = firmBankingResult.getResultCode();

		apply(new RequestFirmBankingFinishedEvent(
			command.getRequestFirmBankingId(),
			command.getRechargeRequestId(),
			command.getMembershipId(),
			command.getToBankName(),
			command.getToBankAccountNumber(),
			command.getMoneyAmount(),
			resultCode,
			id
		));
	}

	@CommandHandler
	public RequestFirmBankingAggregate(@NotNull RollbackFirmBankingRequestCommand command,
		RequestFirmBankingPort firmBankingPort,
		RequestExternalFirmBankingPort externalFirmBankingPort) {
		id = UUID.randomUUID().toString();

		// 법인 계좌 -> 고객 계좌 (롤백)
		firmBankingPort.createFirmBankingRequest(
			new FirmBankingRequest.FromBankName("카카오페이"),
			new FirmBankingRequest.FromBankAccountNumber("123-456-789"),
			new FirmBankingRequest.ToBankName(command.getBankName()),
			new FirmBankingRequest.ToBankAccountNumber(command.getBankAccountNumber()),
			new FirmBankingRequest.MoneyAmount(command.getMoneyAmount()),
			new FirmBankingRequest.FirmBankingStatus(0),
			new FirmBankingRequest.FirmBankingAggregateIdentifier(id));

		// firmBanking!
		externalFirmBankingPort.requestExternalFirmBanking(
			new ExternalFirmBankingRequest(
				"카카오페이",
				"123-456-789",
				command.getBankName(),
				command.getBankAccountNumber(),
				command.getMoneyAmount()
			));

		apply(new RollbackFirmBankingFinishedEvent(
			command.getRollbackFirmBankingId(),
			command.getMembershipId(),
			id)
		);
	}

	@EventSourcingHandler
	public void on(RequestFirmBankingCreatedEvent event) {
		id = UUID.randomUUID().toString();
		fromBankName = event.getFromBankName();
		fromBankAccountNumber = event.getFromBankAccountNumber();
		toBankName = event.getToBankName();
		toBankAccountNumber = event.getToBankAccountNumber();
	}

	@EventSourcingHandler
	public void on(UpdateRequestFirmBankingEvent event) {
		id = event.getAggregateIdentifier();
		firmBankingStatus = event.getFirmBankingStatus();
	}
}
