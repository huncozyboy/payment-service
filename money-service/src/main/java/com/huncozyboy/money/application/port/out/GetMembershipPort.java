package com.huncozyboy.money.application.port.out;

import com.huncozyboy.money.domain.MembershipStatus;

public interface GetMembershipPort {
	MembershipStatus getMembership(String membershipId);
}
