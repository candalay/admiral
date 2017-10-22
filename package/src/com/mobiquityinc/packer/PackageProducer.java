package com.mobiquityinc.packer;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.mobiquityinc.constants.PackageConstants;
import com.mobiquityinc.exception.APIException;

public class PackageProducer {

	static final Pattern lineSeparator = Pattern.compile(PackageConstants.LINE_SEPARATE_PATTERN);
	static final Pattern weightSeparator = Pattern.compile(PackageConstants.WEIGHT_SEPARATE_PATTERN);
	
	private ProductProducer productProducer = new ProductProducer();
	
	public List<Package> generate(String content) {

		if (content.isEmpty()) {
			throw new APIException("File content is empty!");
		}

		List<Package> packages = lineSeparator.splitAsStream(content).map(this::addPackage).collect(toList());

		return checkPackagesByWeight(packages);

	}

	private Package addPackage(String productsAsLine) {
		Matcher matcher = weightSeparator.matcher(productsAsLine);
        if (!matcher.find()) {
            throw new APIException("no package weight.");
        }
        int weight = Integer.parseInt(matcher.group(1));
        
        validateWeight(weight);
        List<Product> products = productProducer.generate(weight, matcher.group(2));
        
        return new Package(weight, products);
	}

	private void validateWeight(int weight) {
		if (weight > PackageConstants.MAX_WEIGHT) {
            throw new APIException("Package weight is more than " + PackageConstants.MAX_WEIGHT);
        }
		
	}

	private List<Package> checkPackagesByWeight(List<Package> packages) {

		packages.removeIf(p1 -> packages.stream()
				.anyMatch(p2 -> p1 != p2 && sumPrice(p1) == sumPrice(p2) && sumWeight(p1) >= sumWeight(p2)));

		return packages;
	}

	private int sumWeight(Package p) {
		return p.products.stream().mapToInt(product -> product.price).sum();
	}

	private double sumPrice(Package p) {
		return p.products.stream().mapToDouble(product -> product.weight).sum();
	}

}
