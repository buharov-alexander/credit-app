package com.bukharov.credit_app.repository;

import java.util.List;
import java.util.UUID;

import com.bukharov.credit_app.entity.OutboxEvent;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OutboxEventRepository extends CrudRepository<OutboxEvent, UUID> {

	@Query("SELECT e FROM OutboxEvent e WHERE e.published = false AND e.retryCount < :maxRetryCount")
	List<OutboxEvent> findEventsToPublish(@Param("maxRetryCount") int maxRetryCount, Pageable pageable);

	@Modifying
	@Query("DELETE FROM OutboxEvent e WHERE e.published = true OR e.retryCount >= :maxRetryCount")
	int cleanEvents(@Param("maxRetryCount") int maxRetryCount);
}
