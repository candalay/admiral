package com.mobiquityinc.packer;

import java.util.ArrayList;
import java.util.List;

public class Package {
	 	public final int maxWeight;
	    public final List<Product> products;

	    public Package(int maxWeight, List<Product> products) {
	        this.maxWeight = maxWeight;
	        this.products = new ArrayList<>(products);
	    }
}
