package com.bukharov.credit_app.entity;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "loans")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class LoanEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	UUID id;

	@Column(name = "client_id", nullable = false)
	UUID clientId;

	@Column(name = "amount", nullable = false, precision = 15, scale = 2)
	BigDecimal amount;

	@Column(name = "term_months", nullable = false)
	Integer termMonths;

	@Column(name = "purpose", length = 500)
	String purpose;
}
