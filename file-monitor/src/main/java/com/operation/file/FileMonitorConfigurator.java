package com.operation.file;

public class FileMonitorConfigurator {

	private String sleepInterval;

	private String defaultFilePath;

	private static FileMonitorConfigurator instance = null;

	private FileMonitorConfigurator() {
	}

	public static FileMonitorConfigurator getInstance() {
		if (instance == null) {
			instance = new FileMonitorConfigurator();
		}

		return instance;
	}
	
	public static FileMonitorConfigurator getInstance(String sleepInterval, String defaultFilePath) {
		if (instance == null) {
			instance = new FileMonitorConfigurator();
			instance.setDefaultFilePath(defaultFilePath);
			instance.setSleepInterval(sleepInterval);
		}

		return instance;
	}

	public long getSleepInterval() {
		return Long.valueOf(sleepInterval);
	}

	public String getDefaultFilePath() {
		return defaultFilePath;
	}

	public void setSleepInterval(String sleepInterval) {
		this.sleepInterval = sleepInterval;
	}

	public void setDefaultFilePath(String defaultFilePath) {
		this.defaultFilePath = defaultFilePath;
	}

}
