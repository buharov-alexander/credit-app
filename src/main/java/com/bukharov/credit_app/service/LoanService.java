package com.bukharov.credit_app.service;

import com.bukharov.credit_app.dto.LoanRequest;
import com.bukharov.credit_app.dto.LoanResponse;
import com.bukharov.credit_app.entity.LoanEntity;
import org.springframework.stereotype.Service;

@Service
public class LoanService {

	public LoanResponse createLoanApplication(LoanRequest request) {
		LoanEntity entity = LoanEntity.builder()
				.clientId(request.clientId())
				.amount(request.amount())
				.termMonths(request.termMonths())
				.purpose(request.purpose())
				.build();
		return new LoanResponse(entity);
	}
}
