package com.dev.monitor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.dev.monitor.model.Services;
import com.dev.monitor.repository.MonitorServiceRepository;

@SpringBootApplication
public class MonitorApplication {

	@Autowired
	private static MonitorServiceRepository repository;

	private static List<Services> servicesList;
	private static boolean connected = false;

	public static void main(String[] arg) throws InterruptedException {
		SpringApplication.run(MonitorApplication.class, arg);

		servicesList = repository.findAll();
		if (servicesList != null) {
			Timer timer = new Timer();
			timer.schedule(task, 01, 5001);
		}
	}

	@Bean
	public CommandLineRunner demo(MonitorServiceRepository repository) {
		return (args) -> {
			MonitorApplication.repository = repository;
			repository.findAll();
		};
	}

	static TimerTask task = new TimerTask() {

		@Override
		public void run() {
			if (connected == false) {
				servicesList = null;
				servicesList = repository.findAll();
				for (Services service : servicesList) {
					System.out.println("Service Name : " + service.getName() + " "
							+ isServiceUp(service.getHost(), service.getPort(), service.getGraceTime()));
					System.out.println('\n');
				}

			}
		}
	};

	public static String isServiceUp(String hostName, int port, String string) {
		boolean isAlive = false;

		SocketAddress socketAddress = new InetSocketAddress(hostName, port);
		Socket socket = new Socket();

		int timeout = Integer.valueOf(string);

		System.out.println("hostName: " + hostName + ", port: " + port);
		try {
			socket.connect(socketAddress, timeout);
			socket.close();
			isAlive = true;

		} catch (SocketTimeoutException exception) {
			System.out.println("SocketTimeoutException " + hostName + ":" + port + ". " + exception.getMessage());
		} catch (IOException exception) {
			System.out.println(
					"IOException - Unable to connect to " + hostName + ":" + port + ". " + exception.getMessage());
		}

		return (isAlive ? "Service is UP" : "Service is down");
	}

}
