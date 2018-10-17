package com.dev.monitor.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.monitor.model.Services;
import com.dev.monitor.repository.MonitorServiceRepository;

@Service
public class MonitorService {

	@Autowired
	private MonitorServiceRepository repository;

	public List<Services> getAllServices() {
		return repository.findAll();
	}

	public List<Services> persist(Services services) {
		repository.save(services);
		return repository.findAll();
	}

}
