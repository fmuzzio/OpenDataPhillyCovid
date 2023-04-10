package edu.upenn.cit5940;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Check if the correct number of runtime arguments were passed
		
		if (args.length != 4) {
	        System.out.println("Error in runtime arguments, set run Configuration arguments in form: <covid_filename> <properties_filename> <population_filename> <log_filename>");
	        return;
	    }
	    
	    // Initialize filenames
	    String covidFilename = args[0];
	    String propertiesFilename = args[1];
	    String populationFilename = args[3];
	    String logFilename = args[4];

	}

}
