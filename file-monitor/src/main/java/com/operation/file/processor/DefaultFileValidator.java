package com.operation.file.processor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import com.operation.file.FileMonitorConstants;

public class DefaultFileValidator implements FileValidator {

	public String validateFile(String filePath) {

		File file = new File(filePath);
		
		try {
			String fileType = Files.probeContentType(file.toPath());
			
			if (!file.exists()) {
				return FileMonitorConstants.NOT_EXISTS_FILE;
			} else if (!fileType.contains("text")) {
				return FileMonitorConstants.INVALID_FILE;
			}
			
		} catch (IOException e) {
			return FileMonitorConstants.INVALID_FILE;
		}
				
		

		return FileMonitorConstants.SUCCESS;
	}

}
