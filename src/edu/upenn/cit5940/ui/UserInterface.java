package edu.upenn.cit5940.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import edu.upenn.cit5940.logging.Logger;
import edu.upenn.cit5940.processor.Processor;

public class UserInterface {
	
	protected Processor processor;
    protected Logger logger;
	
	public UserInterface(Processor processor, Logger logger) {
        this.processor = processor;
        this.logger = logger;
    }
	
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        
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
            
            String userInput = scanner.nextLine();
            
            switch (userInput) {
            
                case "1":
                    // Call method for option 1
                    break;
                
                case "2":
                    // Call method for option 2
                	int population = processor.getTotalZipCodePopulation();
                	System.out.println("BEGIN OUTPUT");
                	System.out.println("The total population for all ZIP Code is: " + population);
                	System.out.println("END OUTPUT\n");
                    break;
                
                case "3":
                    // Call method for option 3
                	
                    String vaccineType = "";
                    while (!vaccineType.equalsIgnoreCase("partial") && !vaccineType.equalsIgnoreCase("full")) {
                        System.out.print("Type \"partial\" or \"full\": ");
                        vaccineType = scanner.nextLine();
                        if (!vaccineType.equalsIgnoreCase("partial") && !vaccineType.equalsIgnoreCase("full")) {
                            System.out.println("Invalid input. Please enter \"partial\" or \"full\".");
                        }
                    }

                    String datePattern = "\\d{4}-\\d{2}-\\d{2}";
                    String date = "";
                    while (!date.matches(datePattern)) {
                        System.out.print("Type a date in the format YYYY-MM-DD: ");
                        date = scanner.nextLine();
                        if (!date.matches(datePattern)) {
                            System.out.println("Invalid input. Please enter a date in the format YYYY-MM-DD.");
                        }
                    }

                    HashMap<Integer, Double> vaccinationsPerCapita = processor.getVaccinationsPerCapita(vaccineType, date);
                    System.out.println("BEGIN OUTPUT");
                    vaccinationsPerCapita.entrySet().stream()
                            .sorted(Map.Entry.comparingByKey())
                            .forEach(entry -> System.out.printf("%05d %.4f%n", entry.getKey(), entry.getValue()));
                    System.out.println("END OUTPUT");
                    break;
                
                case "4":
                    // Call method for option 4
                    break;
                
                case "5":
                    // Call method for option 5
                    break;
                    
                case "6":
                    // Call method for option 6
                	System.out.print("Enter a 5-digit ZIP Code: ");
                    String inputZipCode = scanner.nextLine();

                    int totalMarketValuePerCapita = processor.getTotalMarketValuePerCapita(inputZipCode);
                    System.out.println("BEGIN OUTPUT");
                    System.out.println("Total Market Value Per Capita: " + totalMarketValuePerCapita);
                    System.out.println("END OUTPUT");
                    break;
                    
                case "7":
                    // Call method for option 7
                    break;
                    
                case "0":
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
        
        scanner.close();
    }
}

