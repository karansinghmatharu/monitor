package com.dev.monitor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.monitor.model.Services;
import com.dev.monitor.service.MonitorService;

@RestController
@RequestMapping(value = "/rest/services")
public class MonitorServiceController {

	@Autowired
	private MonitorService monitorService;

	@GetMapping(value = "/all")
	public List<Services> getAllServices() {
		return monitorService.getAllServices();
	}

	@PostMapping(value = "/load")
	public List<Services> persist(@RequestBody final Services services) {
		return monitorService.persist(services);
	}

}
