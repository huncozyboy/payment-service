package com.payment_service.adapter.axon.command;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.payment_service.common.SelfValidating;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateRequestFirmBankingCommand extends SelfValidating<UpdateRequestFirmBankingCommand> {
	@NotNull
	@TargetAggregateIdentifier
	private String aggregateIdentifier;
	private final int firmBankingStatus;

	public UpdateRequestFirmBankingCommand(String aggregateIdentifier, int firmBankingStatus) {
		this.aggregateIdentifier = aggregateIdentifier;
		this.firmBankingStatus = firmBankingStatus;

		this.validateSelf();
	}
}
