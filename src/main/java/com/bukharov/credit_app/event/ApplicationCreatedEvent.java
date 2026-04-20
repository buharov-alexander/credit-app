package com.bukharov.credit_app.event;

import java.math.BigDecimal;
import java.util.UUID;

import com.bukharov.credit_app.entity.LoanEntity;

public record ApplicationCreatedEvent(
		UUID loanId,
		UUID clientId,
		BigDecimal amount,
		Integer termMonths,
		String purpose
) {
	public ApplicationCreatedEvent(LoanEntity entity) {
		this(
				entity.getId(),
				entity.getClientId(),
				entity.getAmount(),
				entity.getTermMonths(),
				entity.getPurpose()
		);
	}
}
