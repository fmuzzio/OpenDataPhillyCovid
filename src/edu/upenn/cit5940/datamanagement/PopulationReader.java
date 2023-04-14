package edu.upenn.cit5940.datamanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.upenn.cit5940.logging.Logger;
import edu.upenn.cit5940.util.Property;


public class PopulationReader extends GeneralReader{
	
	private boolean isValid = false;
	
	private Map<String, HashMap<Integer, Integer>> results = new HashMap<>();
	
	public PopulationReader(String filename) {
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
	public HashMap<Integer, Integer> readPopulation() {
	    // Check if the result is already in the cache
	    if (results.containsKey(filename)) {
	        return results.get(filename);
	    }

	    // If not, read population from the file
	    HashMap<Integer, Integer> populations = readPopulationFromFile();

	    // Store the population in the cache and return them
	    results.put(filename, populations);
	    return populations;
	}

	private HashMap<Integer, Integer> readPopulationFromFile() {
	    HashMap<Integer, Integer> populations = new HashMap<>();
	    String line = "";

	    try {
	        BufferedReader br = new BufferedReader(new FileReader(filename));
	        int i = 0;
	        while((line = br.readLine()) != null) {
	            if(i == 0) {
	                i++;
	                continue;
	            }
	            String[] data = line.split(",");
	            String zip = data[0];
	            int last = zip.lastIndexOf("\"");
	            zip = zip.substring(1, last);
	            int zipCode = Integer.parseInt(zip);
	            int num = Integer.parseInt(data[1]);
	            populations.put(zipCode, num);
	        }
	        br.close();
	    }
	    catch(Exception e) {
	        throw new IllegalStateException(e);
	    }

	    return populations;
	}

}
