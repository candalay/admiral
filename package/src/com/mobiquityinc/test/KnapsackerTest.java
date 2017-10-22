package com.mobiquityinc.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.mobiquityinc.packer.Knapsacker;
import com.mobiquityinc.packer.Product;

public class KnapsackerTest {

	@Test
	public void testKnapsacker() {
		Knapsacker knapsacker = new Knapsacker(
				Arrays.asList(new Product(1, 14.55, 74), new Product(2, 3.98, 16), new Product(3, 26.24, 55), new Product(4, 60.02, 74)),
				75);
		List<Product> products = knapsacker.check();
		Assert.assertTrue(products.size() == 2);
		Assert.assertTrue(products.get(0).index == 4);
		Assert.assertTrue(products.get(1).index == 1);
	}
}
