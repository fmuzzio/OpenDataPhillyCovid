


package edu.upenn.cit5940;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.upenn.cit5940.datamanagement.CovidDataReader;
import edu.upenn.cit5940.datamanagement.CovidReaderFactory;
import edu.upenn.cit5940.datamanagement.PopulationReader;
import edu.upenn.cit5940.datamanagement.PropertyReader;
import edu.upenn.cit5940.logging.Logger;
import edu.upenn.cit5940.processor.Processor;
import edu.upenn.cit5940.ui.UserInterface;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Check if the correct number of runtime arguments were passed
		
		String populationFilename = null;
        String logFilename = null;
        String covidFilename = null;
        String propertiesFilename = null;

        Pattern argumentPattern = Pattern.compile("^--(?<name>.+?)=(?<value>.+)$");

        for (String arg : args) {
            Matcher matcher = argumentPattern.matcher(arg);
            if (matcher.matches()) {
                String name = matcher.group("name");
                String value = matcher.group("value");

                switch (name) {
                    case "population":
                        populationFilename = value;
                        break;
                    case "log":
                        logFilename = value;
                        break;
                    case "covid":
                        covidFilename = value;
                        break;
                    case "properties":
                        propertiesFilename = value;
                        break;
                    default:
                        System.out.println("Unknown argument: " + arg);
                        break;
                }
            } else {
                System.out.println("Invalid argument format: " + arg);
            }
        }

        if (populationFilename == null || logFilename == null || covidFilename == null || propertiesFilename == null) {
            System.out.println("Error: missing required arguments.");
            return;
        }
	    
	    
	    
	    //create covid reader object
	    CovidReaderFactory covidReaderFactory = new CovidReaderFactory();
	    CovidDataReader covidReader = covidReaderFactory.getCovidReader(covidFilename);
	    
	    //create property reader object
	    PropertyReader propReader = new PropertyReader(propertiesFilename);
	    
	    
	    //create population reader object
	    PopulationReader popReader = new PopulationReader(populationFilename);
	     
	   
	    // Create logger and log program start
	    Logger logger = new Logger(logFilename);
	    Logger.initialize(logFilename);
	    
	    String argsString = String.join(" ", args);
	    Logger.log("Command line arguments: " + argsString);

	    
	    // Create Processor object and initialize data (going to want to pass in Reader objects)
	    Processor processor = new Processor(covidReader,propReader,popReader);
	    
	    
	    
	    // Create UI object and run UI
	    UserInterface ui = new UserInterface(processor,logger);
        ui.run();
        
      
	}


}
