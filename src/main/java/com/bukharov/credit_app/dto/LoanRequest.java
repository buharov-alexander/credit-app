package com.bukharov.credit_app.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Builder;

@Builder
public record LoanRequest(
		UUID clientId,
		BigDecimal amount,
		Integer termMonths,
		String purpose
) {
}
