package com.dev.monitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.monitor.model.Services;

public interface MonitorServiceRepository extends JpaRepository<Services, Integer> {}