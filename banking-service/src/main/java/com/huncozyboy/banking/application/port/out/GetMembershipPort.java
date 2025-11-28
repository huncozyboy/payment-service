package com.huncozyboy.banking.application.port.out;

import com.huncozyboy.domain.MembershipStatus;

public interface GetMembershipPort {
	MembershipStatus getMembership(String membershipId);
}
