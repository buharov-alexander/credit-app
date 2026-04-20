package com.bukharov.credit_app.service;

import java.util.Optional;
import java.util.UUID;

import com.bukharov.credit_app.dto.LoanRequest;
import com.bukharov.credit_app.dto.LoanResponse;
import com.bukharov.credit_app.entity.LoanEntity;
import com.bukharov.credit_app.entity.LoanStatus;
import com.bukharov.credit_app.entity.OutboxEvent;
import com.bukharov.credit_app.event.ApplicationCreatedEvent;
import com.bukharov.credit_app.repository.LoanRepository;
import com.bukharov.credit_app.repository.OutboxEventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class LoanService {

	private final LoanRepository repository;
	private final OutboxEventRepository outboxEventRepository;
	private final ObjectMapper objectMapper;

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
		OutboxEvent outboxEvent = createOutboxEvent(entity);
		outboxEventRepository.save(outboxEvent);
		return new LoanResponse(entity);
	}

	private OutboxEvent createOutboxEvent(LoanEntity entity) {
		try {
			return OutboxEvent.builder()
					.eventType("CreateLoanApplication")
					.aggregateId(entity.getId())
					.aggregateType("LoanApplication")
					.payload(objectMapper.writeValueAsString(new ApplicationCreatedEvent(entity)))
					.build();
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Failed to serialize event payload", e);
		}
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
