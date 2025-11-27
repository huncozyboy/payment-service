package com.payment_service.adapter.out;

import com.payment_service.domain.MembershipStatus;

public interface GetMembershipPort {
	MembershipStatus getMembership(String membershipId);
}
