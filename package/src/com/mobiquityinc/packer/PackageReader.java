package com.mobiquityinc.packer;

import static java.nio.file.Files.notExists;
import static java.nio.file.Files.readAllBytes;
import static java.nio.file.Paths.get;

import java.io.IOException;
import java.nio.file.Path;

import com.mobiquityinc.exception.APIException;



public class PackageReader {

	public String readFile(String path) {

		if (null == path || path.equals("")) {
			throw new APIException("Please enter a path");
		}
		Path file = get(path);
		if (notExists(file)) {
			throw new APIException("Path does not exists");
		}
		return getFileContent(path);
	}

	private String getFileContent(String path) {
		try {
			return new String(readAllBytes(get(path)));
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

}
