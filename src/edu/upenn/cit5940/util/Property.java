package edu.upenn.cit5940.util;

public class Property {
	
	private String zip_code;
	private double market_value = 0;
	private double total_livable_area = 0;
	
	public Property(String zip_code, double market_value, double total_livable_area) {
		this.zip_code = zip_code;
		this.market_value = market_value;
		this.total_livable_area = total_livable_area;
	}



	public String getZipCode() {
		return zip_code;
	}

	public void setZipCode(String zip_code) {
		this.zip_code = zip_code;
	}

	public double getMarketValue() {
		return market_value;
	}

	public void setMarketValue(int market_value) {
		this.market_value = market_value;
	}

	public double getTotalLivableArea() {
		return total_livable_area;
	}

	public void setTotalLivableArea(int total_livable_area) {
		this.total_livable_area = total_livable_area;
	}

	

}
