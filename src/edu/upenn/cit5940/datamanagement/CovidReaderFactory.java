package edu.upenn.cit5940.datamanagement;

public class CovidReaderFactory {
	
	public CovidDataReader getCovidReader(String filename) {
		if (filename.endsWith("json")) {
			return new JsonCovidDataReader(filename);
		}
					
		else if (filename.endsWith("csv")) {
			return new CsvCovidDataReader(filename);
	}
	    else {
            throw new IllegalArgumentException("Unsupported file format: " + filename);
        }
	
	
	}
}
