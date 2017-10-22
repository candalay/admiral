package com.mobiquityinc.packer;

import java.util.ArrayList;
import java.util.List;

public class Knapsacker {

	private final int maxWeight;
	private final List<Product> products;
	private final double[][] memory;

	public Knapsacker(List<Product> products, int maxWeight) {
		this.products = products;
		this.maxWeight = maxWeight;
		memory = createMemory();
	}

	private double[][] createMemory() {
		double[][] cache = new double[maxWeight + 1][products.size()];
		for (int j = 0; j < maxWeight + 1; j++) {
			for (int i = 0; i < products.size(); i++) {
				cache[j][i] = -1;
			}
		}
		return cache;
	}

	public List<Product> check() {
		checkMemory(maxWeight, products.size() - 1);
		return checkedProducts();
	}

	private List<Product> checkedProducts() {
		List<Product> checkedProducts = new ArrayList<>();
        int i = products.size() - 1;
        int j = maxWeight;
        while (i >= 0) {
        	Product product = products.get(i);
            double d = i == 0 ? 0 : memory[j][i - 1];
            if (!(Double.compare(memory[j][i],d) == 0)) {
            	checkedProducts.add(product);
                j -= (int) product.weight;
            }
            i--;
        }
        return checkedProducts;
	}

	private double checkMemory(int j, int i) {
		if (i < 0 || j < 0) {
            return 0;
        }
        Product product = products.get(i);
        double d1, d2, result = memory[j][i];
        if (result == -1) {
            if (product.weight > j) {
            	d1 = -1;
            } else {
            	d1 = product.price + checkMemory(j - (int) product.weight, i - 1);
            }
            d2 = checkMemory(j, i - 1);
            result = Math.max(d1, d2);
            memory[j][i] = result;
        }
        return result;
		
	}

}
