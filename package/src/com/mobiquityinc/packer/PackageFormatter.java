package com.mobiquityinc.packer;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PackageFormatter {

	public static final String EMPTY_PRODUCTS_MARK = "-";

	public static final String PRODUCTS_SEPARATOR = ",";

	public String format(List<Package> packageList) {
		return packageList.stream().map(p -> p.products).map(this::toProductsString)
				.collect(Collectors.joining(System.lineSeparator()));
	}

	private String toProductsString(List<Product> products) {
		if (products.isEmpty()) {
			return EMPTY_PRODUCTS_MARK;
		}
		return products.stream().map(item -> item.index).map(Objects::toString)
				.collect(Collectors.joining(PRODUCTS_SEPARATOR));
	}
}
