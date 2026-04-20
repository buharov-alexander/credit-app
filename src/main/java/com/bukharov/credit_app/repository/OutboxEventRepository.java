package com.bukharov.credit_app.repository;

import java.util.UUID;

import com.bukharov.credit_app.entity.OutboxEvent;
import org.springframework.data.repository.CrudRepository;

public interface OutboxEventRepository extends CrudRepository<OutboxEvent, UUID> {
}
