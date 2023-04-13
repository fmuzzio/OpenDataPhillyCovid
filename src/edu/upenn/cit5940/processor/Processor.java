package edu.upenn.cit5940.processor;

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
	
    private GetAverage get_average; //Leo
    
    protected int populationSum = 0;
    
	protected HashMap<Integer, Integer> populations;
	protected List<Covid> coviddata;
	protected List<Property> properties;
 	
	public Processor(CovidDataReader covidreader,PropertyReader propreader,PopulationReader popreader) {
		covidDataReader = covidreader;
		propertiesReader = propreader;
		populationReader = popreader;
		
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
	        int population = populations.getOrDefault(zipCode, 0);
	     

	        if (population > 0) {
	            int vaccinations;
	            if (vaccineType.equalsIgnoreCase("partial")) {
	                vaccinations = covidEntry.getPartiallyVaccinated();
	            } else {
	                vaccinations = covidEntry.getFullyVaccinated();
	            }

	            double perCapita = (double) vaccinations / population;
	            vaccinationsPerCapita.put(zipCode, perCapita);
	        }
	     }
	   }

	    return vaccinationsPerCapita;
	}
	
	
	//section 3.4/3.5
	
	public GetAverage getGetAverage() {
		return this.get_average;
	}
		
	public void setGetAverage(GetAverage get_average) {
		this.get_average = get_average;
	}
	
	//section 3.6
	public int getTotalMarketValuePerCapita(String zipCode) {
		
	    populations = populationReader.readPopulation();
	    properties = propertiesReader.getAllProperty();
	    
	    int population = 0;
	    double totalMarketValue = 0;
	    int intZipCode;
	    
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


	
	

}
