package com.huncozyboy.money.application.service;

import com.huncozyboy.common.UseCase;
import com.huncozyboy.money.adapter.axon.command.CreateMemberMoneyCommand;
import com.huncozyboy.money.application.port.in.CreateMemberMoneyRequestCommand;
import com.huncozyboy.money.application.port.in.CreateMemberMoneyRequestUseCase;
import com.huncozyboy.money.application.port.out.CreateMemberMoneyPort;
import com.huncozyboy.money.domain.MemberMoney.MembershipId;
import com.huncozyboy.money.domain.MemberMoney.MoneyAggregateIdentifier;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;

@UseCase
@RequiredArgsConstructor
public class CreateMoneyRequestService implements CreateMemberMoneyRequestUseCase {

	private final CreateMemberMoneyPort createMemberMoneyPort;
	private final CommandGateway commandGateway;

	@Override
	public void createMoney(CreateMemberMoneyRequestCommand command) {
		CreateMemberMoneyCommand axonCommand = new CreateMemberMoneyCommand(
			command.getTargetMembershipId());
		commandGateway.send(axonCommand).whenComplete((result, throwable) -> {
			if (throwable != null) {
				throw new RuntimeException(throwable);
			} else {
				createMemberMoneyPort.createMemberMoney(
					new MembershipId(command.getTargetMembershipId()),
					new MoneyAggregateIdentifier(result.toString())
				);
			}
		});
	}
}
