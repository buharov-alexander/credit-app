package com.bukharov.credit_app.service;

import java.util.Optional;
import java.util.UUID;

import com.bukharov.credit_app.dto.LoanRequest;
import com.bukharov.credit_app.dto.LoanResponse;
import com.bukharov.credit_app.entity.LoanEntity;
import com.bukharov.credit_app.entity.LoanStatus;
import com.bukharov.credit_app.repository.LoanRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class LoanService {

	private LoanRepository repository;

	@Transactional
	public LoanResponse createLoanApplication(LoanRequest request) {
		LoanEntity entity = LoanEntity.builder()
				.clientId(request.clientId())
				.amount(request.amount())
				.termMonths(request.termMonths())
				.purpose(request.purpose())
				.status(LoanStatus.DRAFT)
				.build();
		entity = repository.save(entity);
		return new LoanResponse(entity);
	}

	@Transactional
	public LoanResponse getLoanApplication(UUID id) {
		Optional<LoanEntity> entity = repository.findById(id);
		if (entity.isEmpty()) {
			throw new IllegalArgumentException("Loan Application is not found");
		}
		return new LoanResponse(entity.get());
	}
}
