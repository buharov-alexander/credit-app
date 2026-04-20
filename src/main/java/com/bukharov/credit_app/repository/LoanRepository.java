package com.bukharov.credit_app.repository;

import java.util.UUID;

import com.bukharov.credit_app.entity.LoanEntity;
import org.springframework.data.repository.CrudRepository;

public interface LoanRepository extends CrudRepository<LoanEntity, UUID> {
}
