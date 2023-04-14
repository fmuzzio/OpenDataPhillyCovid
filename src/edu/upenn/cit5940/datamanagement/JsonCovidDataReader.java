package edu.upenn.cit5940.datamanagement;

import edu.upenn.cit5940.logging.Logger;
import edu.upenn.cit5940.util.Covid;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class JsonCovidDataReader extends GeneralReader implements CovidDataReader {
	
    private String fileName;
    private boolean isValid = false;
    
  

    public JsonCovidDataReader(String fileName) {
        this.fileName = fileName;
        this.isValid = checkFileValidity();
    }
	
    @Override
    public boolean checkFileValidity() {
		if(this.fileName.endsWith("json")) {
		isValid = true;
		}
		return isValid;
	}

    @Override
    public List<Covid> getCovidData() {
    	
    	Logger.getInstance();
    	Logger.log("Reading in: "+ fileName);
    	
        List<Covid> covidData = new ArrayList<>();
        JSONParser parser = new JSONParser();

        try {
            FileReader reader = new FileReader(fileName);
            JSONArray jsonArray = (JSONArray) parser.parse(reader);
            //reg expression for checking "YYYY-MM-DD hh:mm:ss"  format
            Pattern timestampPattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}$");

            for (Object object : jsonArray) {
                JSONObject jsonObject = (JSONObject) object;
                
                String timestamp = (String) jsonObject.get("etl_timestamp");
                long zipCodeNum = (long) jsonObject.get("zip_code");
                String zipCodeStr = String.format("%05d", zipCodeNum); {
                
                if (timestampPattern.matcher(timestamp).matches() && zipCodeStr.length() == 5) {
                	
	                // we check each parameter to see if null. if not null then get the value else assign a 0
	                int zipCode = ((Number) jsonObject.get("zip_code")).intValue();
	                int negativeTests = jsonObject.get("NEG") != null ? ((Number) jsonObject.get("NEG")).intValue() : 0;
	                int positiveTests = jsonObject.get("POS") != null ? ((Number) jsonObject.get("POS")).intValue() : 0;
	                int hospitalized = jsonObject.get("hospitalized") != null ? ((Number) jsonObject.get("hospitalized")).intValue() : 0;
	                int deaths = jsonObject.get("deaths") != null ? ((Number) jsonObject.get("deaths")).intValue() : 0;
	                int partiallyVaccinated = jsonObject.get("partially_vaccinated") != null ? ((Number) jsonObject.get("partially_vaccinated")).intValue() : 0;
	                int fullyVaccinated = jsonObject.get("fully_vaccinated") != null ? ((Number) jsonObject.get("fully_vaccinated")).intValue() : 0;
	                int boosted = jsonObject.get("boosted") != null ? ((Number) jsonObject.get("boosted")).intValue() : 0;
	
	                Covid covidObj = new Covid(zipCode, negativeTests, positiveTests, hospitalized, deaths, partiallyVaccinated, fullyVaccinated,boosted,timestamp);
	                covidData.add(covidObj);
                	}
                
                }
            }
            
        } catch (IOException | ParseException e) {
        	//logger.log("Exception occurred while reading JSON data: " + e.getMessage());
            e.printStackTrace();
        }

        return covidData;
    }
}
