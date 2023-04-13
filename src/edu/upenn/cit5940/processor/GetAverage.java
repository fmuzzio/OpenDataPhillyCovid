package edu.upenn.cit5940.processor;

import java.util.List;

import edu.upenn.cit5940.util.Property;

public interface GetAverage {
	
	public int getAverage(String zip_code, List<Property> dataset);
}
