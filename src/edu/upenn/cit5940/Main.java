package edu.upenn.cit5940;

import edu.upenn.cit5940.logging.Logger;
import edu.upenn.cit5940.ui.UserInterface;

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
	    String populationFilename = args[3];
	    String logFilename = args[4];
	    
	    
	   // Create logger and log program start
	    Logger logger = Logger.getInstance(logFilename);
	    
	    
	    // Create Reader objects
	    
	    
	    
	    // Create Processor object and initialize data (going to want to pass in Reader objects)
	    
	    
	    
	    
	    // Create UI object and run UI, (going to want to pass in Processor and Logger objects)
	    UserInterface ui = new UserInterface();
        ui.run();

	}

}
