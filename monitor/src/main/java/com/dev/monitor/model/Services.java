package com.dev.monitor.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Services {

	@Id
	private Integer id;
	private String name;
	private String host;
	private int port;
	private int frequency;
	private String startTime;
	private String endTime;
	private String graceTime;//timeout
	public Services() {
	}
	
	public Services(int id, String name, String host, int port, int frequency, String startTime, String endTime,String graceTime) {
		super();
		this.id = id;
		this.name = name;
		this.host = host;
		this.port = port;
		this.frequency = frequency;
		this.startTime = startTime;
		this.endTime = endTime;
		this.graceTime=graceTime;
	}
	public String getGraceTime() {
		return graceTime;
	}

	public void setGraceTime(String graceTime) {
		this.graceTime = graceTime;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
