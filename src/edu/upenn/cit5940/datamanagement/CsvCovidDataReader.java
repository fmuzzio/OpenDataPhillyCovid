package edu.upenn.cit5940.datamanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.upenn.cit5940.logging.Logger;
import edu.upenn.cit5940.util.Covid;

public class CsvCovidDataReader extends GeneralReader implements CovidDataReader   {
	
	protected Logger logger;
	private String fileName;
	private boolean isValid = false;
	
	private Map<String, List<Covid>> results = new HashMap<>();

    public CsvCovidDataReader(String fileName) {
        this.fileName = fileName;
        this.isValid = checkFileValidity();
	}
	
    @Override
	public boolean checkFileValidity() {
		if(this.fileName.endsWith("csv")) {
		isValid = true;
		}
		return isValid;
	}
    
    
    //implementing Memoization 
    @Override
    public List<Covid> getCovidData() {
        // Check if the result is already in the cache
        if (results.containsKey(fileName)) {
            return results.get(fileName);
        }

        // If not, read Covid data from the file
        List<Covid> covidData = getCovidDataFromCsvFile();

        // Store the Covid data in the cache and return them
        results.put(fileName, covidData);
        return covidData;
    }

    private List<Covid> getCovidDataFromCsvFile() {
        Logger.getInstance();
        Logger.log("Reading in: " + fileName);

        List<Covid> covidData = new ArrayList<>();
        // Regular expression for checking "YYYY-MM-DD hh:mm:ss" format
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
                    Covid covidObj = new Covid(zipCode, negativeTests, positiveTests, hospitalized, deaths, partiallyVaccinated, fullyVaccinated, boosted, etlTimestamp);
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
    

    
    
    
    

}
