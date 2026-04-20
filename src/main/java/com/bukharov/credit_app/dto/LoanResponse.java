package com.bukharov.credit_app.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.bukharov.credit_app.entity.LoanEntity;
import lombok.Builder;

@Builder
public record LoanResponse(
		UUID id,
		UUID clientId,
		BigDecimal amount,
		Integer termMonths,
		String purpose) {

	public LoanResponse(LoanEntity entity) {
		this(
				entity.getId(),
				entity.getClientId(),
				entity.getAmount(),
				entity.getTermMonths(),
				entity.getPurpose()
		);
	}
}
