package edu.upenn.cit5940;

import java.util.ArrayList;
import java.util.List;

import edu.upenn.cit5940.datamanagement.CsvCovidDataReader;
import edu.upenn.cit5940.datamanagement.JsonCovidDataReader;
import edu.upenn.cit5940.logging.Logger;
import edu.upenn.cit5940.processor.Processor;
import edu.upenn.cit5940.ui.UserInterface;
import edu.upenn.cit5940.util.Covid;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Check if the correct number of runtime arguments were passed
		
		if (args.length != 4) {
	        System.out.println("Error in runtime arguments, set run Configuration arguments in form: <covid_filename> <properties_filename> <population_filename> <log_filename>");
	        return;
	    }
	    
	     //Initialize filenames
	    String covidFilename = args[0];
	    String propertiesFilename = args[1];
	    String populationFilename = args[2];
	    String logFilename = args[3];
	    
	    
	    
	    
	    // Create Reader objects, also add one for properties and population maybe
	    List<Covid> coviddata = fileExtensionCheck(covidFilename);
	    
	    

        for (Covid covid : coviddata) {
            System.out.println(covid);
            
       }
        
      
       
     
	    
	   // Create logger and log program start
	    //Logger logger = Logger.getInstance(logFilename);
	    
	    
	    
	    
	    
	    
	    // Create Processor object and initialize data (going to want to pass in Reader objects)
	    //Processor processor = new Processor();
	    
	    
	    
	    // Create UI object and run UI, (going to want to pass in Processor and Logger objects)
	    UserInterface ui = new UserInterface();
        ui.run();
        
        
    
	}
	
	 public static List<Covid> fileExtensionCheck(String file) {
         List<Covid> zipcodes = new ArrayList<>();

         if (file.endsWith("json")) {
             JsonCovidDataReader jsonCovidDataReader = new JsonCovidDataReader(file);
             zipcodes = jsonCovidDataReader.getCovidData();
         } else if (file.endsWith("csv"))  {
             CsvCovidDataReader csvCovidDataReader = new CsvCovidDataReader(file);
             zipcodes = csvCovidDataReader.getCovidData();
         }
         else {
        	 System.out.println("Invalid file extension. Only 'json' and 'csv' are supported.");
         }

         return zipcodes;
     }

}
