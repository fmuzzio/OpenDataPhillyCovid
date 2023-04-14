package edu.upenn.cit5940.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import edu.upenn.cit5940.logging.Logger;
import edu.upenn.cit5940.processor.Processor;

public class UserInterface {
	
	protected Processor processor;
    protected Logger logger;
	
	public UserInterface(Processor processor, Logger logger) {
        this.processor = processor;
        this.logger = logger;
    }
	
	public void displayZipCodes() {
		Set<String> zipCode = processor.getAllUniqueZipCodes();
    	int columnCounter = 0;
    	for (String zipcode : zipCode) {
            System.out.print(zipcode + "\t");

            columnCounter++;
            if (columnCounter % 5 == 0) {
                System.out.println();
            }
        }
	}
	
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        
        Logger.getInstance();
        
        while (!exit) {
            System.out.println("Please select an option:");
            System.out.println("1 - List available actions");
            System.out.println("2 - Total population for all ZIP Codes");
            System.out.println("3 - Total partial or full vaccinations per capita");
            System.out.println("4 - Average market value");
            System.out.println("5 - Average total livable area");
            System.out.println("6 - Total market value per capita");
            System.out.println("7 - Custom operation");
            System.out.println("0 - Exit");
            System.out.flush();
            System.out.print("> ");
            
            
            String userInput = scanner.nextLine();
            
            Logger.log("User chose option: "+ userInput);
            
            switch (userInput) {
            	
            
                case "1":
                    // Call method for option 1
                	System.out.println("\nBEGIN OUTPUT");
                	List<Integer> actions = processor.getAvailableActions();
                	
                	for(Integer action: actions) {
                		System.out.println(action);
                	}
                	System.out.println("END OUTPUT\n");
                    break;
                
                case "2":
                    // Call method for option 2
                	int population = processor.getTotalZipCodePopulation();
                	System.out.println("\nBEGIN OUTPUT");
                	System.out.println("The total population for all ZIP Code is: " + population);
                	System.out.println("END OUTPUT\n");
                    break;
                
                case "3":
                    // Call method for option 3
                	
                    String vaccineType = "";
                    while (!vaccineType.equalsIgnoreCase("partial") && !vaccineType.equalsIgnoreCase("full")) {
                        System.out.println("Type \"partial\" or \"full\" ");
                        System.out.flush();
                        System.out.print("> ");
                        vaccineType = scanner.nextLine();
                        Logger.log("Vaccine Type Chosen: "+ vaccineType);
                        
                        if (!vaccineType.equalsIgnoreCase("partial") && !vaccineType.equalsIgnoreCase("full")) {
                            System.out.println("Invalid input. Please enter \"partial\" or \"full\".");
                        }
                    }

                    String datePattern = "\\d{4}-\\d{2}-\\d{2}";
                    String date = "";
                    while (!date.matches(datePattern)) {
                        System.out.println("Type a date in the format YYYY-MM-DD");
                        System.out.flush();
                        System.out.print("> ");
                        date = scanner.nextLine();
                        Logger.log("Date Selected: "+ date);
                        
                        if (!date.matches(datePattern)) {
                            System.out.println("Invalid input. Please enter a date in the format YYYY-MM-DD.");
                        }
                    }

                    HashMap<Integer, Double> vaccinationsPerCapita = processor.getVaccinationsPerCapita(vaccineType, date);
                    System.out.println("\nBEGIN OUTPUT");
                    vaccinationsPerCapita.entrySet().stream()
                            .sorted(Map.Entry.comparingByKey())
                            .forEach(entry -> System.out.printf("%05d %.4f%n", entry.getKey(), entry.getValue()));
                    System.out.println("END OUTPUT\n");
                    break;
                
                    
                case "4":
                	
                	displayZipCodes();
                	System.out.println("\nFrom the Choices Above, Enter a 5-digit ZIP Code");
                	System.out.flush();
                    System.out.print("> ");
                    String inputZipCode = scanner.nextLine();
                    Logger.log("Zip Code Selected: "+ inputZipCode);
                    
                	int average = processor.getAverageMarketValue(inputZipCode);
                	System.out.println("\nBEGIN OUTPUT");
                	System.out.println("Average Market Value For Given Zip Code: "+ average);
                	System.out.println("END OUTPUT\n");
                    // Call method for option 4
                    break;
                
                    
                case "5":
                    // Call method for option 5
                    break;
                    
                    
                case "6":
                    // Call method for option 6
                	
                	displayZipCodes();
                	System.out.println("\nFrom the Choices Above, Enter a 5-digit ZIP Code ");
                	System.out.flush();
                    System.out.print("> ");
                    String inputZipCode2 = scanner.nextLine();
                    
                    Logger.log("Zip Code Selected: "+ inputZipCode2);

                    int totalMarketValuePerCapita = processor.getTotalMarketValuePerCapita(inputZipCode2);
                    System.out.println("\nBEGIN OUTPUT");
                    System.out.println("Total Market Value Per Capita: " + totalMarketValuePerCapita);
                    System.out.println("END OUTPUT\n");
                    break;
                    
                    
                case "7":
                    // Call method for option 7
                	
                	displayZipCodes();
                	System.out.println("\nFrom the Choices Above, Enter a 5-digit ZIP Code ");
                	System.out.flush();
                    System.out.print("> ");
                    String inputZipCode3 = scanner.nextLine();
                    
                    Logger.log("Zip Code Selected: "+ inputZipCode3);

                    Map<Integer, List<Double>> retrievedValues = processor.getMostCovidCasesPerCapita(inputZipCode3);
                    List<Double> values = retrievedValues.get(Integer.parseInt(inputZipCode3));

                    if (values != null && values.size() == 2) {
                        double retrievedCovidCasesPerCapita = values.get(0);
                        double retrievedTotalLivableArea = values.get(1);

                        System.out.println("\nBEGIN OUTPUT");
                        System.out.println("The Zip Code With The Most Covid Cases Per Capita and Its Total Livable Area Is As Follows: ");
                        System.out.println("Zip code: " + inputZipCode3);
                        System.out.println("Total livable area: " + retrievedTotalLivableArea);
                        System.out.println("Covid cases per capita: " + retrievedCovidCasesPerCapita);
                        System.out.println("END OUTPUT\n");
                    } else {
                        System.out.println("No data found for the given ZIP Code.");
                    }
                    break;

                    
                    
                case "0":
                	System.out.println("Goodbye!");
                	Logger.log("Exiting Program..");
                    exit = true;
                    break;
                
                default:
                    System.out.println("\nInvalid option. Please try again.\n");
                    Logger.log("\nInvalid option. Please try again.\n");
                    break;
            }
        }
        
        scanner.close();
    }
   
    
    
}

