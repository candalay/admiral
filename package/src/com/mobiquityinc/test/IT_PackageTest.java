package com.mobiquityinc.test;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Path;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.mobiquityinc.packer.PackageFormatter;
import com.mobiquityinc.packer.Packer;

public class IT_PackageTest {

	@Rule
	public TemporaryFolder folder = new TemporaryFolder();

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@After
	public void cleanUpStreams() {
		System.setOut(null);
	}

	@Test
	public void testPacker() {
		String content = String.join(System.lineSeparator(),
				"81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)",
				"8 : (1,15.3,€34)",
				"75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)",
				"56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)");
		Path file = createTempFile(content, folder);
		String[] args = { file.toString() };
		Packer.main(args);
		String expected = String.join(System.lineSeparator(), "4", PackageFormatter.EMPTY_PRODUCTS_MARK,
				"2" + PackageFormatter.PRODUCTS_SEPARATOR + "7", "8" + PackageFormatter.PRODUCTS_SEPARATOR + "9",
				System.lineSeparator()).trim();

		String result = outContent.toString().trim();

		Assert.assertEquals(expected, result);

	}

	public static Path createTempFile(String content, TemporaryFolder temporaryFolder) {
		try {
			File file = temporaryFolder.newFile();
			try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
				bw.write(content);
			}
			return file.toPath();
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}

}
