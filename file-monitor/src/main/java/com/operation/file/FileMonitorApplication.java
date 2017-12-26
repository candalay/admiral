package com.operation.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import com.operation.file.monitor.FileMonitorWebSocketHandler;
import com.operation.file.service.DefaultMonitorService;
import com.operation.file.service.FileMonitorService;

@Configuration
@EnableAutoConfiguration
@EnableWebSocket
public class FileMonitorApplication extends SpringBootServletInitializer implements WebSocketConfigurer {

	@Autowired
	private Environment env;

	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(monitorFileWebSocketHandler(), "/monitor").withSockJS();
	}

	private WebSocketHandler monitorFileWebSocketHandler() {
		return new FileMonitorWebSocketHandler(monitorFileService());
	}

	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}

	@Bean
	public FileMonitorService monitorFileService() {
		return new DefaultMonitorService("");
	}

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(FileMonitorApplication.class);
		Environment env = app.run(args).getEnvironment();
		
		FileMonitorConfigurator.getInstance(env.getProperty("file.monitor.sleep.interval"), env.getProperty("file.path"));

	}

}
