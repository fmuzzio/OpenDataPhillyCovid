package edu.upenn.cit594.util;

public class Population {
	
	private String zipCode;
	private int population;
	
	public Population (String zipCode, int population) {
		this.population = population;
		this.zipCode = zipCode;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
  
  public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

}
