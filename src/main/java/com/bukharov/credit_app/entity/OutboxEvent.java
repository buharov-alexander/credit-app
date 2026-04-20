package com.bukharov.credit_app.entity;

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
@Table(name = "outbox_events")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class OutboxEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;

	@Column(name = "aggregate_type", nullable = false, length = 100)
	private String aggregateType;

	@Column(name = "aggregate_id", nullable = false)
	private UUID aggregateId;

	@Column(name = "event_type", nullable = false, length = 100)
	private String eventType;

	@Column(name = "payload", nullable = false, columnDefinition = "TEXT")
	private String payload;

	@Column(name = "retry_count")
	@Builder.Default
	private int retryCount = 0;
}
