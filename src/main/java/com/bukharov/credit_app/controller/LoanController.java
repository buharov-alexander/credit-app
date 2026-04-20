package com.bukharov.credit_app.controller;

import com.bukharov.credit_app.dto.LoanRequest;
import com.bukharov.credit_app.dto.LoanResponse;
import com.bukharov.credit_app.service.LoanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/loan")
public class LoanController {

	private LoanService loanService;

	@PostMapping
	public ResponseEntity<LoanResponse> createLoanApplication(
			@RequestBody LoanRequest request
	) {
		LoanResponse response = loanService.createLoanApplication(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

}
