package com.flightconfig.flightconfigapi.domain.audit;

import com.flightconfig.flightconfigapi.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
    List<AuditLog> findByUser(User user);
}