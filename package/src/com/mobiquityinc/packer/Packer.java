package com.mobiquityinc.packer;

import java.util.List;

public class Packer {

	private static Packer instance = null;

	private PackageReader reader = new PackageReader();

	protected Packer() {

	}

	public static Packer getInstance() {
		if (instance == null) {
			instance = new Packer();
		}
		return instance;
	}

	public static String pack(String path) {

		return getInstance().getBestPackage(path);
	}

	public static void main(String[] args) {
		if (args.length == 0) {
			System.err.println("Please enter a file path");
			return;
		}

		String result = pack(args[0]);
		
		System.out.println(result);
	}

	public String getBestPackage(String path) {

		String content = reader.readFile(path);
		List<Package> packageList = new PackageProducer().generate(content);
		return convertResultToString(packageList);
	}

	private String convertResultToString(List<Package> packageList) {
		return new PackageFormatter().format(packageList);
	}
}
