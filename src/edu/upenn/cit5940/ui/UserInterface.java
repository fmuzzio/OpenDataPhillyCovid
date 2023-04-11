package edu.upenn.cit5940.ui;

import java.util.Scanner;

public class UserInterface {
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        
        while (!exit) {
            System.out.println("Please select an option:");
            System.out.println("1 - List available actions");
            System.out.println("2 - Total population for all ZIP Codes");
            System.out.println("3 - Total,partial, or full vaccinations per capita");
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
                    break;
                case "3":
                    // Call method for option 3
                    break;
                case "4":
                    // Call method for option 4
                    break;
                case "5":
                    // Call method for option 5
                    break;
                case "6":
                    // Call method for option 6
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

