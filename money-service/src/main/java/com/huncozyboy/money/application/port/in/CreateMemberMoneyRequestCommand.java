package com.huncozyboy.money.application.port.in;

import com.huncozyboy.common.SelfValidating;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Builder
@Data
@EqualsAndHashCode(callSuper = false)
public class CreateMemberMoneyRequestCommand extends SelfValidating<CreateMemberMoneyRequestCommand> {
	@NotNull
	private final String targetMembershipId;

	public CreateMemberMoneyRequestCommand(String targetMembershipId) {
		this.targetMembershipId = targetMembershipId;

		this.validateSelf();
	}
}