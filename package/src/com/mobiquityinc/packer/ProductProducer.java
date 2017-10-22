package com.mobiquityinc.packer;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.mobiquityinc.constants.PackageConstants;
import com.mobiquityinc.exception.APIException;

public class ProductProducer {

	static final Pattern productSeparator = Pattern.compile(PackageConstants.PRODUCT_SEPARATE_PATTERN);
	static final Pattern productsSeparator = Pattern.compile(PackageConstants.PRODUCTS_SEPARATE_PATTERN);

	public List<Product> generate(int weight, String productsAsLine) {
		if (productsAsLine.isEmpty()) {
			return Collections.emptyList();
		}

		List<Product> products = productsSeparator.splitAsStream(productsAsLine).map(this::toProduct)
				.sorted((i1, i2) -> Double.compare(i1.weight, i2.weight)).collect(Collectors.toList());
		checkProductsAmount(products);
		return checkByWeight(products, weight);
	}

	private Product toProduct(String line) {
		Matcher matcher = productSeparator.matcher(line);

		if (!matcher.find()) {
			throw new APIException("Product line is incorrect format: " + line);
		}
		int index = Integer.parseInt(matcher.group(1));
		double weight = Double.parseDouble(matcher.group(2));
		checkWeight(weight);
		int price = Integer.parseInt(matcher.group(3));
		checkPrice(price);
		return new Product(index, weight, price);
	}

	private void checkPrice(int price) {
		if (price > PackageConstants.MAX_PRICE) {
			throw new APIException("MAX_PRICE is exceeded");
		}
	}

	private void checkWeight(double weight) {
		if (weight > PackageConstants.MAX_WEIGHT) {
			throw new APIException("MAX_WEIGHT is exceeded");
		}
	}

	private List<Product> checkByWeight(List<Product> products, int weight) {
		Knapsacker checker = new Knapsacker(products, weight);
		return checker.check().stream().sorted((p1, p2) -> Double.compare(p1.index, p2.index)).collect(Collectors.toList());
	}

	private void checkProductsAmount(List<Product> products) {
		if (products.size() > PackageConstants.MAX_PRODUCTS_SIZE) {
			throw new APIException("MAX_PRODUCTS_SIZE is exceeded");
		}
	}

}
