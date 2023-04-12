package edu.upenn.cit5940.datamanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.upenn.cit5940.util.Covid;

public class CsvCovidDataReader extends GeneralReader implements CovidDataReader   {
	
	private String fileName;

    public CsvCovidDataReader(String fileName) {
        this.fileName = fileName;
    }
    
    @Override
    public List<Covid> getCovidData() {
    	List<Covid> covidData = new ArrayList<>();
    	//reg expression for checking "YYYY-MM-DD hh:mm:ss"  format
    	Pattern timestampPattern = Pattern.compile("^\\d{1,2}/\\d{1,2}/\\d{4} \\d{2}:\\d{2}$");


        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            // Skip header line
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                int zipCode = parseIntOrDefault(values[0], 0);
                String zipCodeStr = String.format("%05d", zipCode);
                
                int negativeTests = parseIntOrDefault(values[1], 0);
                int positiveTests = parseIntOrDefault(values[2], 0);
                int deaths = parseIntOrDefault(values[3], 0);
                int hospitalized = parseIntOrDefault(values[4], 0);
                int partiallyVaccinated = parseIntOrDefault(values[5], 0);
                int fullyVaccinated = parseIntOrDefault(values[6], 0);
                int boosted = parseIntOrDefault(values[7], 0);
                String etlTimestamp = values[8];

                Matcher timestampMatcher = timestampPattern.matcher(etlTimestamp);
                if (timestampMatcher.matches() && zipCodeStr.length() == 5) {
                    
                	Covid covidObj = new Covid(zipCode, negativeTests, positiveTests, hospitalized, deaths, partiallyVaccinated, fullyVaccinated, boosted,etlTimestamp);
                	covidData.add(covidObj);
                    // Log the processing of a Zipcode (maybe add a logging method here)
                    //logger.log("Processed Zipcode: " + zipCode);
                }
            }
        } catch (IOException e) {
        	//logger.log("Exception occurred while reading JSON data: " + e.getMessage());
            e.printStackTrace();
        }
        
        return covidData;
    }
    

    private int parseIntOrDefault(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
    
    
    

}
