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
	
	
	
    
    //old method for reading data 
	public List<Property> getAllProperty(){
		
		
		List<Property> properties =  new ArrayList<Property>();

		
		try {
			
			File file = new File(this.filename);
			FileReader fileReader;
			fileReader = new FileReader(file);
			
			
			BufferedReader br = new BufferedReader(fileReader);
			String thisLine;
			String[] split;
			
			int livable_area_n = 0;
			String total_livable_area_s;
			double total_livable_area;
			
			
			int market_value_n = 0;
			String market_value_s;
			double market_value;
			
			
			int zip_code_n = 0;
			String zip_code_s;
			String zip_code;
			
			
			try {
				
				thisLine = br.readLine();
				split = thisLine.split(",");
				
				
				for (int i=0; i < split.length; i++) {
					
				
					if (split[i].toLowerCase().contains("total") && split[i].toLowerCase().contains("livable") && split[i].toLowerCase().contains("area")) {
						livable_area_n = i;
					}
					
			
					if (split[i].toLowerCase().contains("market") && split[i].toLowerCase().contains("value")) {
						market_value_n = i;
					}
					
				
					if (split[i].toLowerCase().contains("zip")&& split[i].toLowerCase().contains("code")) {
						zip_code_n = i;
					}
				}
					
			
				while((thisLine = br.readLine()) != null) {
					
					split = thisLine.split(",");
					
					
					total_livable_area_s = split[livable_area_n].replaceAll("[^a-zA-Z0-9]", "");
					market_value_s = split[market_value_n].replaceAll("[^a-zA-Z0-9]", "");
					zip_code_s = split[zip_code_n].replaceAll("[^a-zA-Z0-9]", "");
					
					
					try {
						Integer.parseInt(zip_code_s);
						market_value = Double.parseDouble(market_value_s);
						total_livable_area = Double.parseDouble(total_livable_area_s);
						zip_code = zip_code_s.substring(0, 5);
						properties.add(new Property(zip_code, market_value, total_livable_area));
						
					}catch (Exception e) {continue;}
				}
				
				br.close();
				
			} catch (IOException e) { e.printStackTrace();}
			
			
		} catch (FileNotFoundException e) { e.printStackTrace();}
		
		
		return properties;
		
	}
	
}
