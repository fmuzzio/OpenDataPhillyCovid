package edu.upenn.cit5940.datamanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class PopulationReader extends GeneralReader{
	
	public PopulationReader(String name) {
		this.filename = name;
	}
	
	
	
	// method 2:
	public HashMap<Integer, Integer> readPopulation() {
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
