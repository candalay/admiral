package com.operation.file.monitor;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.operation.file.FileMonitorConfigurator;
import com.operation.file.FileMonitorConstants;
import com.operation.file.processor.DefaultFileProcessor;
import com.operation.file.processor.DefaultFileValidator;
import com.operation.file.processor.FileProcessor;
import com.operation.file.processor.FileValidator;
import com.operation.file.service.FileMonitorService;

public class FileMonitorWebSocketHandler extends TextWebSocketHandler {
	
	private final FileMonitorService monitorService;
	
	private final FileValidator fileValidator;
	
	private static Logger logger = LoggerFactory.getLogger(FileMonitorWebSocketHandler.class);


	public FileMonitorWebSocketHandler(FileMonitorService monitorService) {
		this.monitorService = monitorService;
		this.fileValidator = new DefaultFileValidator();
	}
	
	@Override
	public void afterConnectionEstablished(WebSocketSession session) {
		logger.debug("Opened new session in instance " + this);
	}

	@Override
	public void handleTextMessage(WebSocketSession session, TextMessage message)
			throws Exception {
		String file = FileMonitorConfigurator.getInstance().getDefaultFilePath();
		logger.debug(file);
		
		String result = fileValidator.validateFile(file);
		
		if(!result.equals(FileMonitorConstants.SUCCESS)) {
			session.sendMessage(new TextMessage(result));		
		} else {
			getFileProcessor().processFile(session, file);
			
		}
	}
	

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception)
			throws Exception {
		session.close(CloseStatus.SERVER_ERROR);
	}

	public FileProcessor getFileProcessor() {
		return new DefaultFileProcessor();
	}
}
