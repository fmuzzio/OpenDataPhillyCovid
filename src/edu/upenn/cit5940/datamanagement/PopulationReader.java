package edu.upenn.cit5940.datamanagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import edu.upenn.cit5940.util.Population;

public class PopulationReader extends GeneralReader{
	
	public PopulationReader(String name) {
		this.filename = name;
	}
	
	
	public List<Population> getPopulation() {
		List<Population> Populations = new ArrayList<Population>();
		String line = "";
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			int i = 0;
		    while((line = br.readLine())!= null) {
		    	if(i == 0) {
		    		i++;
		    		continue;
		    	}
		    	 String[] data = line.split(",");
			     String zip = data[0];
			     int last = zip.lastIndexOf("\"");
			     zip = zip.substring(1, last);
			     int num = Integer.parseInt(data[1]);
			     Populations.add(new Population(zip,num));
			}
		    br.close();
		}
		catch(Exception e) {
      throw new IllegalStateException(e);
    }
		
    return Populations;		
		
	}

}
