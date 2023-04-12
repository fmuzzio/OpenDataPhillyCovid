package edu.upenn.cit5940.processor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.upenn.cit5940.datamanagement.CovidDataReader;
import edu.upenn.cit5940.datamanagement.PopulationReader;
import edu.upenn.cit5940.datamanagement.PropertyReader;
import edu.upenn.cit5940.logging.Logger;
import edu.upenn.cit5940.util.Covid;

public class Processor {
	
	
	protected CovidDataReader covidDataReader;
    protected PropertyReader propertiesReader;
    protected PopulationReader populationReader;
    protected Logger logger;
    
    protected int populationSum = 0;
	protected HashMap<Integer, Integer> populations;
	protected List<Covid> coviddata;
	
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

	
	

}
