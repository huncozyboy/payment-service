package com.huncozyboy.money.application.port.out;

import com.huncozyboy.money.domain.MemberMoney;
import com.huncozyboy.money.domain.MoneyChangingRequest;

public interface IncreaseMoneyPort {
	MoneyChangingRequest createMoneyChangingRequest(
		MoneyChangingRequest.TargetMembershipId targetMembershipId,
		MoneyChangingRequest.MoneyChangingType moneyChangingType,
		MoneyChangingRequest.ChangingMoneyAmount changingMoneyAmount,
		MoneyChangingRequest.MoneyChangingStatus moneyChangingStatus,
		MoneyChangingRequest.Uuid uuid
	);

	MemberMoney increaseMoney(
		MemberMoney.MembershipId memberId,
		int increaseMoneyAmount
	);
}
