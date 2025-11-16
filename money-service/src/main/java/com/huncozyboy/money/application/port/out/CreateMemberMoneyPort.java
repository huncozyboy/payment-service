package com.huncozyboy.money.application.port.out;

import com.huncozyboy.money.domain.MemberMoney;

public interface CreateMemberMoneyPort {
	void createMemberMoney(
		MemberMoney.MembershipId memberId,
		MemberMoney.MoneyAggregateIdentifier aggregateIdentifier
	);
}
