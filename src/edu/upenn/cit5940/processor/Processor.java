package edu.upenn.cit5940.processor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

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
		this.covidDataReader = covidreader;
		this.propertiesReader = propreader;
		this.populationReader = popreader;
		
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
		 
		 //Logger.log();
		
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
	
	public int getAverage(String zipCode, AverageStrategy averageStrategy) {
	    properties = propertiesReader.getAllProperties();

	    if (zipCode.length() != 5) {
	        System.out.print("The length of zipcode is invalid");
	        return 0;
	    }

	    try {
	        Integer.parseInt(zipCode);
	    } catch (Exception e) {
	        System.out.print("Invalid zip code");
	        return 0;
	    }

	    List<Property> zipData = properties.stream()
	            .filter(p -> p.getZipCode().equals(zipCode))
	            .collect(Collectors.toList());

	    if (zipData.isEmpty()) {
	        return 0;
	    }

	    return (int) averageStrategy.calculateAverage(zipData);
	}
	
	// implementing Strategy Design pattern
	//section 3.4
	public int getAverageMarketValue(String zipCode) {
	    return getAverage(zipCode, new AverageMarketValueStrategy());
	}
	
	// implementing Strategy Design pattern
	//section 3.5
	public int getAverageTotalLivableArea(String zipCode) {
	    return getAverage(zipCode, new AverageTotalLivableAreaStrategy());
	}

		
		
	
	//section 3.6
	public int getTotalMarketValuePerCapita(String zipCode) {
		
	    populations = populationReader.readPopulation();
	    properties = propertiesReader.getAllProperties();
	    
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
	
	//section 3.7: method will produce most reported number of  positive covid cases per capita and total_livable_area based on zip code for on all timestamps: 
	
	public Map.Entry<Integer, List<Double>> getMostCovidCasesPerCapita() {
	    coviddata = covidDataReader.getCovidData();
	    populations = populationReader.readPopulation();
	    properties = propertiesReader.getAllProperties();

	    Map<Integer, List<Double>> data = new HashMap<>();

	    for (Map.Entry<Integer, Integer> populationEntry : populations.entrySet()) {
	        int zipCode = populationEntry.getKey();
	        int population = populationEntry.getValue();
	        double totalLivableArea = 0.0;
	        int totalCovidCases = 0;

	        if (population > 0) {
	            for (Property property : properties) {
	                if (property.getZipCode().equals(String.valueOf(zipCode))) {
	                    totalLivableArea += property.getTotalLivableArea();
	                }
	            }

	            for (Covid covidEntry : coviddata) {
	                if (covidEntry.getZipCode() == zipCode) {
	                    totalCovidCases += covidEntry.getPositiveTests();
	                }
	            }

	            double covidCasesPerCapita = (double) totalCovidCases / population;
	            List<Double> values = new ArrayList<>();
	            values.add(covidCasesPerCapita);
	            values.add(totalLivableArea);

	            data.put(zipCode, values);
	        }
	    }

	    return data.entrySet().stream()
	            .max(Comparator.comparingDouble(entry -> entry.getValue().get(0)))
	            .orElse(null);
	}

	
	//helper method to make user interaction more informative, using Tree set to display all unique zipcodes 
	public Set<String> getAllUniqueZipCodes() {
		properties = propertiesReader.getAllProperties();
		
	    Set<String> uniqueZipCodes = new TreeSet<>();

	    for (Property property : properties) {
	        uniqueZipCodes.add(property.getZipCode());
	    }

	    return uniqueZipCodes;
	}




	
	

}
