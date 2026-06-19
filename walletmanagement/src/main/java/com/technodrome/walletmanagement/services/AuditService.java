package com.technodrome.walletmanagement.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.technodrome.walletmanagement.model.AuditLog;
import com.technodrome.walletmanagement.repository.AuditRepository;

@Service
public class AuditService {

    @Autowired
    private AuditRepository repository;

    public void log(Long userId, String action) {

        AuditLog audit = new AuditLog();

        audit.setUserId(userId);
        audit.setAction(action);
        audit.setCreatedDate(LocalDateTime.now());

        repository.save(audit);
    }
}