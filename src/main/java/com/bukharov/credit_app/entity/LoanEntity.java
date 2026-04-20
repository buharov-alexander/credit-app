package com.bukharov.credit_app.entity;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LoanEntity {

	UUID id;
	UUID clientId;
	BigDecimal amount;
	Integer termMonths;
	String purpose;
}
