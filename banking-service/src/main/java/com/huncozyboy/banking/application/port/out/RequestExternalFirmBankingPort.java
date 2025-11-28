package com.huncozyboy.banking.application.port.out;

import com.huncozyboy.domain.ExternalFirmBankingRequest;
import com.huncozyboy.domain.FirmBankingResult;

public interface RequestExternalFirmBankingPort {
	FirmBankingResult requestExternalFirmBanking(ExternalFirmBankingRequest request);
}
