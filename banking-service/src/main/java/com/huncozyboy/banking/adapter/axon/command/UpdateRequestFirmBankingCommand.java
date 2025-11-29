package com.huncozyboy.banking.adapter.axon.command;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import com.huncozyboy.common.SelfValidating;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class UpdateRequestFirmBankingCommand extends SelfValidating<UpdateRequestFirmBankingCommand> {
	private final int firmBankingStatus;
	@NotNull
	@TargetAggregateIdentifier
	private String aggregateIdentifier;

	public UpdateRequestFirmBankingCommand(String aggregateIdentifier, int firmBankingStatus) {
		this.aggregateIdentifier = aggregateIdentifier;
		this.firmBankingStatus = firmBankingStatus;

		this.validateSelf();
	}
}
