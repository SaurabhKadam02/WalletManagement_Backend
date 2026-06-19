package com.technodrome.walletmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.technodrome.walletmanagement.model.AuditLog;

@Repository
public interface AuditRepository
        extends JpaRepository<AuditLog,Long> {
}
