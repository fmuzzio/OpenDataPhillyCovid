package edu.upenn.cit5940.datamanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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
import edu.upenn.cit5940.util.Property;

public class PropertyReader extends GeneralReader {

	private String filename;
	 private boolean isValid = false;
	 private Map<String, List<Property>> results = new HashMap<>();

	public PropertyReader(String filename) {
		this.filename = filename;
		this.isValid = checkFileValidity();
	}
	
	public boolean checkFileValidity() {
		if(this.filename.endsWith("csv")) {
		isValid = true;
		}
		return isValid;
	}
	
	
	//implementing Memoization 
	public List<Property> getAllProperties() {
        // Check if the result is already in the cache
        if (results.containsKey(filename)) {
            return results.get(filename);
        }

        // If not, read properties from the file
        List<Property> properties = readPropertiesFromFile();

        // Store the properties in the cache and return them
        results.put(filename, properties);
        return properties;
    }

    private List<Property> readPropertiesFromFile() {
        Logger.getInstance();
        Logger.log("Reading in: " + filename);

        List<Property> properties = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            // Skip header line
            br.readLine();

            while ((line = br.readLine()) != null) {

                String[] values = line.split(",");
                double marketValue = parseDoubleOrDefault(values[35], 0);
                double totalLivableArea = parseDoubleOrDefault(values[65], 0);
                int zipCode = parseIntOrDefault(values[73], 0);
                String zipCodeStr = String.format("%05d", zipCode);

                if (zipCodeStr.length() == 5) {
                    properties.add(new Property(zipCodeStr, marketValue, totalLivableArea));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }
	

    	
}
