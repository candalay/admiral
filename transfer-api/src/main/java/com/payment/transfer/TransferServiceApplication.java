package com.payment.transfer;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class TransferServiceApplication {

	private static final Logger log = LoggerFactory.getLogger(TransferServiceApplication.class);

	public static void main(String[] args) throws UnknownHostException {

		SpringApplication app = new SpringApplication(TransferServiceApplication.class);
		Environment env = app.run(args).getEnvironment();

		if (env.getActiveProfiles().length == 0) {
			log.warn("No profile found, running with default configuration");
			return;
		}

		StringBuilder appMsg = new StringBuilder();
		appMsg.append("\n**********************************************************************");
		appMsg.append("\n\tApplication '{}' is running on http://{}:{}");
		appMsg.append("\n\tRunning profile : {}");
		appMsg.append("\n\tDocumentation URL @ http://{}:{}/swagger-ui.html");
		appMsg.append("\n**********************************************************************");

		log.info(appMsg.toString(), env.getProperty("spring.application.name"),
				InetAddress.getLocalHost().getHostAddress(), env.getProperty("server.port"),
				Arrays.toString(env.getActiveProfiles()), InetAddress.getLocalHost().getHostAddress(),
				env.getProperty("server.port"));

	}
}
