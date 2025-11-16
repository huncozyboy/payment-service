package com.huncozyboy.money.application.port.out;

import com.huncozyboy.money.domain.MemberMoney;

public interface GetMemberMoneyPort {
	MemberMoney getMemberMoney(
		MemberMoney.MembershipId memberId
	);
}
