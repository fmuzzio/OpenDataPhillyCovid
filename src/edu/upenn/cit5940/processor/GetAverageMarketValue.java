package edu.upenn.cit5940.processor;

import java.util.ArrayList;
import java.util.List;
import edu.upenn.cit5940.util.Property;

public class GetAverageMarketValue implements GetAverage{
	
	
	
	public int getAverage(String zip_code, List<Property> dataset) {
		
		List<Property> zipData = new ArrayList<Property>();
		double total_market_value = 0;
		
		
		if(zip_code.length() != 5) { 
			System.out.print("The length of zip_code is invalid");
			return 0;
		}
		
		try {
			Integer.parseInt(zip_code);
			
		}catch (Exception e) {
			System.out.print("invalid zip code");
			return 0;
		}
		
		
		for (Property property: dataset) {
			if(property.getZipCode().equals(zip_code)) {
				zipData.add(property);
			}
		} 
				
		if(zipData.isEmpty()) {return 0;}
				
				
		for(Property zipCode: zipData) {
			total_market_value = total_market_value + zipCode.getMarketValue();
		}
				
		int avg_market_value = (int)(total_market_value / zipData.size());
				
		return avg_market_value;
				
	}		
			
}
