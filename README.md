Covid an Property Data Analysis Tool
A Java-based application that processes property, population, and COVID-19 data to provide insights and statistics for users interested in the property market.

Table of Contents
-Features
-Getting Started
  Prerequisites
  Installation
  Usage
-Project Structure

Features
Display all unique ZIP codes in the dataset
Calculate average market value for properties within a given ZIP code
Calculate average total livable area for properties within a given ZIP code
Display total population for a given ZIP code
Determine the ZIP code with the highest COVID-19 cases per capita and its total livable area

Getting Started
  Prerequisites
  Java Development Kit (JDK) 8 or later

  Installation 
  -Clone the repository:
  git clone https://github.com/fmuzzio/OpenDataPhillyCovid.git
  -Open the project in your favorite Java IDE (e.g., IntelliJ IDEA or Eclipse).
  -Configure the project's run configurations with the following runtime arguments:
       --population=population.csv --log=log.txt --covid=covid_data.json --properties=properties.csv
  Replace population.csv, log.txt, covid_data.json, and properties.csv with your actual input file paths.

Usage
Run the Main class in your Java IDE.
The application will present a menu with options for you to choose from.
Enter the desired option number and follow the prompts to obtain the required information.

Project Structure
src/main/java: Contains the source code for the application, organized into packages:
datamanagement: Contains classes responsible for reading and caching data from input files.
processor: Contains the Processor class that processes the data and calculates the required statistics.
ui: Contains the UserInterface class that handles user input and displays output.
util: Contains utility classes, such as Logger.

