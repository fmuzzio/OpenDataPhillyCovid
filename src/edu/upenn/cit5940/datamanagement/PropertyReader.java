package edu.upenn.cit5940.datamanagement;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import edu.upenn.cit5940.util.Property;

public class PropertyReader {

	private String filename;

	public PropertyReader(String name) {
		this.filename = name;
	}
		
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
