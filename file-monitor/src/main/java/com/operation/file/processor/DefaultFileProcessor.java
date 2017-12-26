package com.operation.file.processor;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import com.operation.file.FileMonitorConfigurator;

public class DefaultFileProcessor implements Runnable, FileProcessor {

	private static long updateInterval = FileMonitorConfigurator.getInstance().getSleepInterval();
	private long filePointer;
	private File file;
	private WebSocketSession session;
	private static volatile boolean keepRunning = true;
	private static Logger logger = LoggerFactory.getLogger(DefaultFileProcessor.class);

	public DefaultFileProcessor() {
	}
	
	public DefaultFileProcessor(WebSocketSession session, File file) {
		this.file = file;
		this.session = session;
	}

	public void processFile(WebSocketSession session, String file) {

		final Thread mainThread = Thread.currentThread();
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				keepRunning = false;
				try {
					mainThread.join();
				} catch (InterruptedException ex) {
					logger.debug("Exception: {}", ex);
				}
			}
		});
		File log = new File(file);
		DefaultFileProcessor monitor = new DefaultFileProcessor(session, log);
		new Thread(monitor).start();

	}

	public void run() {
		try {

			while (keepRunning) {
				Thread.sleep(updateInterval);
				long len = file.length();

				if (len < filePointer) {
					// Log must have been jibbled or deleted.
					this.session.sendMessage(new TextMessage("File was reset, starting to monitoring again"));
					filePointer = len;
				} else if (len > filePointer) {
					// File must have had something added to it!
					RandomAccessFile raf = new RandomAccessFile(file, "r");
					raf.seek(filePointer);
					String line = null;

					int infoCount = 0;
					int warningCount = 0;
					int errorCount = 0;

					while ((line = raf.readLine()) != null) {
						String[] arrLine;
						arrLine = line.split("\\s+");

						if (arrLine.length > 2) {

							if (arrLine[2].contains("INFO")) {
								infoCount++;
							}

							if (arrLine[2].contains("ERROR")) {
								errorCount++;
							}

							if (arrLine[2].contains("WARNING")) {
								errorCount++;
							}
						}
					}

					StringBuilder report = new StringBuilder();
					report.append("INFO:");
					report.append(String.valueOf(infoCount));
					report.append("\n");
					report.append("ERROR:");
					report.append(String.valueOf(errorCount));
					report.append("\n");
					report.append("WARNING:");
					report.append(String.valueOf(warningCount));
					report.append("\n");

					this.session.sendMessage(new TextMessage(report.toString()));

					filePointer = raf.getFilePointer();
					raf.close();
				}
			}
		} catch (Exception e) {
			try {
				this.session.sendMessage(new TextMessage("Fatal error reading log file, log tailing has stopped."));
			} catch (IOException exception) {
				logger.debug("Exception: {}", exception);
			}
		}

	}
}
