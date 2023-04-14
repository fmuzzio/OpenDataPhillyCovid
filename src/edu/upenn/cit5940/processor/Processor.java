package edu.upenn.cit5940.processor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import edu.upenn.cit5940.datamanagement.CovidDataReader;
import edu.upenn.cit5940.datamanagement.PopulationReader;
import edu.upenn.cit5940.datamanagement.PropertyReader;
import edu.upenn.cit5940.logging.Logger;
import edu.upenn.cit5940.util.Covid;
import edu.upenn.cit5940.util.Property;

public class Processor {
	
	
	protected CovidDataReader covidDataReader;
    protected PropertyReader propertiesReader;
    protected PopulationReader populationReader;
    protected Logger logger;

    
    protected int populationSum = 0;
    
	protected HashMap<Integer, Integer> populations;
	protected List<Covid> coviddata;
	protected List<Property> properties;
 	
	public Processor(CovidDataReader covidreader,PropertyReader propreader,PopulationReader popreader) {
		covidDataReader = covidreader;
		propertiesReader = propreader;
		populationReader = popreader;
		
    }
	
	
	//section 3.1
	
	public List<Integer> getAvailableActions() {
	    List<Integer> availableActions = new ArrayList<>();
	    availableActions.add(0); // Exit
	    availableActions.add(1); // Display available actions

	    if (covidDataReader.checkFileValidity() && populationReader.checkFileValidity()) {
	        availableActions.add(2);
	        availableActions.add(3);
	        
	    }
	    
	    if (propertiesReader.checkFileValidity() && populationReader.checkFileValidity()) {
	        availableActions.add(4);
	        availableActions.add(6);
	        
	    }
	    
	    if (propertiesReader.checkFileValidity()) {
	        availableActions.add(5);
	       
	    }
	  
	    if (covidDataReader.checkFileValidity() && populationReader.checkFileValidity() && propertiesReader.checkFileValidity()) {
	        availableActions.add(7);
	       
	    }

	    return availableActions;
	}
	
	
	
	//section 3.2 
	public int getTotalZipCodePopulation() {
		
		 populations = populationReader.readPopulation();
		
		for (Map.Entry<Integer,Integer> entry : populations.entrySet()) {
	        Integer value = entry.getValue();
	        populationSum+= value;
	    }
	    
		return populationSum;
	}
	
	//section 3.3 
	public HashMap<Integer, Double> getVaccinationsPerCapita(String vaccineType, String date) {

	    populations = populationReader.readPopulation();
	    coviddata = covidDataReader.getCovidData();

	    HashMap<Integer, Double> vaccinationsPerCapita = new HashMap<>();

	    for (Covid covidEntry : coviddata) {
	        if (covidEntry.getTimestamp().contains(date)) {
	            int zipCode = covidEntry.getZipCode();
	            if (populations.containsKey(zipCode)) {
	                int population = populations.get(zipCode);

	                if (population > 0) {
	                    int vaccinations;
	                    if (vaccineType.equalsIgnoreCase("partial")) {
	                        vaccinations = covidEntry.getPartiallyVaccinated();
	                    } else {
	                        vaccinations = covidEntry.getFullyVaccinated();
	                    }

	                    if (vaccinations > 0) {
	                        double perCapita = (double) vaccinations / population;
	                        vaccinationsPerCapita.put(zipCode, perCapita);
	                    }
	                }
	            }
	        }
	    }

	    return vaccinationsPerCapita;
	}
	
	//3.4
	public int getAverageMarketValue(String zip_code) {
	    properties = propertiesReader.getAllProperties();
	    
	    List<Property> zipData = new ArrayList<Property>();
	    double total_market_value = 0;
	    
	    if(zip_code.length() != 5) { 
	        System.out.print("The length of zipcode is invalid");
	        return 0;
	    }
	    
	    try {
	        Integer.parseInt(zip_code);
	    } catch (Exception e) {
	        System.out.print("invalid zip code");
	        return 0;
	    }
	    
	    for (Property property: properties) {
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
		
			
	
	//section 3.5
	public int getAverage(String zip_code, List<Property> dataset) {
		
		List<Property> zipData = new ArrayList<Property>();
		double total_livable_area = 0;
		
		
		if(zip_code.length() != 5) { 
			System.out.print("The length of zipcode is invalid");
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
			total_livable_area = total_livable_area + zipCode.getTotalLivableArea();
		}
		
		int avg_livable_area = (int)(total_livable_area / zipData.size());
		
		return avg_livable_area;
		
	}
		
	
	//section 3.6
	public int getTotalMarketValuePerCapita(String zipCode) {
		
	    populations = populationReader.readPopulation();
	    properties = propertiesReader.getAllProperty();
	    
	    int population = 0;
	    double totalMarketValue = 0;
	    int intZipCode;
	    
	    if(zipCode.length() != 5) { 
			System.out.print("The length of zipcode is invalid");
			return 0;
		}
	    
	    try {
	    	
	        intZipCode = Integer.parseInt(zipCode);
	    } 
	    catch (NumberFormatException e) {
	        return 0;
	    }
	    
	    if (populations.containsKey(intZipCode)) {
	        population = populations.get(intZipCode);
	    } else {
	        return 0;
	    }

	    for (Property property : properties) {
	        if (property.getZipCode().equals(zipCode)) {
	            totalMarketValue += property.getMarketValue();
	        }
	    }

	    if (population == 0 || totalMarketValue == 0) {
	        return 0;
	    } else {
	        return (int) (totalMarketValue / population);
	    }
	}
	
	//section 3.7: method will produce total number of reported positive covid cases per capita and total_livable_area based on inputted zip code for on all timestamps: 
	// we need to use the three datasets 
	
	public Map<Integer, List<Double>> getMostCovidCasesPerCapita(String zipCode) {
	    coviddata = covidDataReader.getCovidData();
	    populations = populationReader.readPopulation();
	    properties = propertiesReader.getAllProperties();

	    Map<Integer, List<Double>> data = new HashMap<>();

	    int population = populations.getOrDefault(Integer.parseInt(zipCode), 0);
	    double totalLivableArea = 0.0;
	    int totalCovidCases = 0;
	    
	    if(zipCode.length() != 5) { 
			System.out.print("The length of zipcode is invalid");
			return new HashMap<>();
		}

	    if (population > 0) {
	        for (Property property : properties) {
	            if (property.getZipCode().contains(zipCode)) {
	                totalLivableArea += property.getTotalLivableArea();
	            }
	        }

	        for (Covid covidEntry : coviddata) {
	            if (covidEntry.getZipCode() == Integer.parseInt(zipCode)) {
	                totalCovidCases += covidEntry.getPositiveTests();
	            }
	        }

	        double covidCasesPerCapita = (double) totalCovidCases / population;
	        List<Double> values = new ArrayList<>();
	        values.add(covidCasesPerCapita);
	        values.add(totalLivableArea);

	        data.put(Integer.parseInt(zipCode), values);
	    }

	    return data;
	}




	
	

}
