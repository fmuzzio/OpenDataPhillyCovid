package edu.upenn.cit5940.datamanagement;


public abstract class GeneralReader {
	
	protected String filename;
	
	public int parseIntOrDefault(String value, int defaultValue) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
	
	public double parseDoubleOrDefault(String value, double defaultValue) {
	    try {
	        return Double.parseDouble(value);
	    } catch (NumberFormatException e) {
	        return defaultValue;
	    }
	}
	

}
