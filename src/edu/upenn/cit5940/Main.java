package edu.upenn.cit5940;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.upenn.cit5940.datamanagement.CovidDataReader;
import edu.upenn.cit5940.datamanagement.CovidReaderFactory;
import edu.upenn.cit5940.datamanagement.CsvCovidDataReader;
import edu.upenn.cit5940.datamanagement.JsonCovidDataReader;
import edu.upenn.cit5940.datamanagement.PopulationReader;
import edu.upenn.cit5940.datamanagement.PropertyReader;
import edu.upenn.cit5940.logging.Logger;
import edu.upenn.cit5940.processor.Processor;
import edu.upenn.cit5940.ui.UserInterface;
import edu.upenn.cit5940.util.Covid;
import edu.upenn.cit5940.util.Population;
import edu.upenn.cit5940.util.Property;

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
	    
	    
	    
	    //create covid reader object
	    CovidReaderFactory covidReaderFactory = new CovidReaderFactory();
	    CovidDataReader covidReader = covidReaderFactory.getCovidReader(covidFilename);
	    
	    //create property reader object
	    PropertyReader propReader = new PropertyReader(propertiesFilename);
	    
	    //testing to see if property objects were created properly
	    List<Property> properties = propReader.getAllProperty();
	    for(Property property:properties) {
	    	 System.out.println(property);
	    }
	    
	    //create population reader object
	    PopulationReader popReader = new PopulationReader(populationFilename);
	     
	   
	    // Create logger and log program start
	    Logger logger = new Logger(logFilename);
	    //Logger logger = Logger.getInstance(logFilename);
	    
	    
	   
	    // Create Processor object and initialize data (going to want to pass in Reader objects)
	    Processor processor = new Processor(covidReader,propReader,popReader);
	    
	    
	    
	    // Create UI object and run UI
	    UserInterface ui = new UserInterface(processor,logger);
        ui.run();
        
      
	}


}
