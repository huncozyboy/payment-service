package com.huncozyboy.banking.domain;

import java.util.UUID;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FirmBankingRequest {

	private final String firmBankingRequestId;
	private final String fromBankName;
	private final String fromBankAccountNumber;
	private final String toBankName;
	private final String toBankAccountNumber;
	private final int moneyAmount; // won
	private final UUID uuid;
	private final String aggregateIdentifier;
	private int firmBankingStatus; // 0: 요청, 1: 완료, 2: 실패

	public static FirmBankingRequest generateFirmBankingRequest(
		FirmBankingRequestId firmBankingingRequestId,
		FromBankName fromBankName,
		FromBankAccountNumber fromBankAccountNumber,
		ToBankName toBankName,
		ToBankAccountNumber toBankAccountNumber,
		MoneyAmount moneyAmount,
		FirmBankingStatus firmBankingingStatus,
		UUID uuid,
		FirmBankingAggregateIdentifier firmBankingAggregateIdentifier
	) {
		return new FirmBankingRequest(
			firmBankingingRequestId.getFirmBankingRequestId(),
			fromBankName.getFromBankName(),
			fromBankAccountNumber.getFromBankAccountNumber(),
			toBankName.getToBankName(),
			toBankAccountNumber.getToBankAccountNumber(),
			moneyAmount.getMoneyAmount(),
			firmBankingingStatus.firmBankingStatus,
			uuid,
			firmBankingAggregateIdentifier.getAggregateIdentifier()
		);
	}

	public void setCompleted() {
		this.firmBankingStatus = 1;
	}

	public void setFailed() {
		this.firmBankingStatus = 2;
	}

	@Value
	public static class FirmBankingRequestId {

		String firmBankingRequestId;

		public FirmBankingRequestId(String value) {
			this.firmBankingRequestId = value;
		}
	}

	@Value
	public static class FromBankName {

		String fromBankName;

		public FromBankName(String value) {
			this.fromBankName = value;
		}
	}

	@Value
	public static class FromBankAccountNumber {

		String fromBankAccountNumber;

		public FromBankAccountNumber(String value) {
			this.fromBankAccountNumber = value;
		}
	}

	@Value
	public static class ToBankName {

		String toBankName;

		public ToBankName(String value) {
			this.toBankName = value;
		}
	}

	@Value
	public static class ToBankAccountNumber {

		String toBankAccountNumber;

		public ToBankAccountNumber(String value) {
			this.toBankAccountNumber = value;
		}
	}

	@Value
	public static class MoneyAmount {

		int moneyAmount;

		public MoneyAmount(int value) {
			this.moneyAmount = value;
		}
	}

	@Value
	public static class FirmBankingStatus {

		int firmBankingStatus;

		public FirmBankingStatus(int value) {
			this.firmBankingStatus = value;
		}
	}

	@Value
	public static class FirmBankingAggregateIdentifier {

		String aggregateIdentifier;

		public FirmBankingAggregateIdentifier(String value) {
			this.aggregateIdentifier = value;
		}
	}
}
