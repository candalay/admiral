package com.operation.file.test;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import com.operation.file.FileMonitorApplication;
import com.operation.file.FileMonitorConfigurator;
import com.operation.file.client.FileMonitorStartService;
import com.operation.file.client.SimpleClientWebSocketHandler;
import com.operation.file.client.SimpleFileMonitorStartService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FileMonitorApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class FileMonitorTest {

	private static Log logger = LogFactory.getLog(FileMonitorTest.class);

	@LocalServerPort
	private int port = 1234;

	@Test
	public void echoEndpoint() throws Exception {

		Map<String, Object> propertiesMap = new HashMap<String, Object>();

		propertiesMap.put("websocket.uri", "ws://localhost:" + this.port + "/monitor/websocket");
		propertiesMap.put("file.monitor.sleep.interval", "5000");
		propertiesMap.put("file.path", "C:/test.txt");

		FileMonitorConfigurator.getInstance(propertiesMap.get("file.monitor.sleep.interval").toString(),
				propertiesMap.get("file.path").toString());

		ConfigurableApplicationContext context = new SpringApplicationBuilder(ClientConfiguration.class,
				PropertyPlaceholderAutoConfiguration.class).properties(propertiesMap)
						.run("--spring.main.web_environment=false");
		long count = context.getBean(ClientConfiguration.class).latch.getCount();
		
		AtomicReference<String> messagePayloadReference = context.getBean(ClientConfiguration.class).messagePayload;
		context.close();
		assertThat(count).isEqualTo(0);

		StringBuilder result = new StringBuilder();
		result.append("INFO:");
		result.append(String.valueOf(30));
		result.append("\n");
		result.append("ERROR:");
		result.append(String.valueOf(0));
		result.append("\n");
		result.append("WARNING:");
		result.append(String.valueOf(0));
		result.append("\n");

		assertThat(messagePayloadReference.get()).isEqualTo(result.toString());
	}

	@Configuration
	static class ClientConfiguration implements CommandLineRunner {

		@Value("${websocket.uri}")
		private String webSocketUri;

		private final CountDownLatch latch = new CountDownLatch(1);

		private final AtomicReference<String> messagePayload = new AtomicReference<String>();

		@Override
		public void run(String... args) throws Exception {
			logger.info("Waiting for response: latch=" + this.latch.getCount());
			if (this.latch.await(10, TimeUnit.SECONDS)) {
				logger.info("Got response: " + this.messagePayload.get());
			} else {
				logger.info("Response not received: latch=" + this.latch.getCount());
			}
		}

		@Bean
		public WebSocketConnectionManager wsConnectionManager() {

			WebSocketConnectionManager manager = new WebSocketConnectionManager(client(), handler(), this.webSocketUri);
			manager.setAutoStartup(true);

			return manager;
		}

		@Bean
		public StandardWebSocketClient client() {
			return new StandardWebSocketClient();
		}

		@Bean
		public SimpleClientWebSocketHandler handler() {
			return new SimpleClientWebSocketHandler(startService(), this.latch, this.messagePayload);
		}

		@Bean
		public FileMonitorStartService startService() {
			return new SimpleFileMonitorStartService();
		}

	}

}
