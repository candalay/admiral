package com.operation.file.processor;

import org.springframework.web.socket.WebSocketSession;

public interface FileProcessor {
	
	void processFile(WebSocketSession session, String file);

}
