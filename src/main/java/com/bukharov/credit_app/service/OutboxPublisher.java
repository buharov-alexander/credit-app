package com.bukharov.credit_app.service;

import com.bukharov.credit_app.entity.OutboxEvent;
import com.bukharov.credit_app.repository.OutboxEventRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@AllArgsConstructor
public class OutboxPublisher {

	public static final int MAX_RETRY_COUNT = 3;
	private final OutboxEventRepository outboxEventRepository;

	@Scheduled(fixedDelay = 5000)
	@Transactional
	public void publishEvents() {
		var events = outboxEventRepository.findEventsToPublish(MAX_RETRY_COUNT, PageRequest.ofSize(100));
		for (OutboxEvent event : events) {
			try {
				publishEvent(event);
				event.setPublished(true);
			} catch (Exception e) {
				log.error("Cannot publish event: {}", event.getId());
				event.setRetryCount(event.getRetryCount() + 1);
			}
			outboxEventRepository.save(event);
		}
	}

	private void publishEvent(OutboxEvent event) {
		log.info("Publish event: {}", event.getPayload());
	}

	@Scheduled(cron="0 0 2 * * *")
	@Transactional
	public void clean() {
		int deleted = outboxEventRepository.cleanEvents(MAX_RETRY_COUNT);
		log.info("Cleaned up {} old outbox events", deleted);
	}
}
