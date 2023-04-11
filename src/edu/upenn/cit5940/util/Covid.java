package edu.upenn.cit594.util;

public class Covid {
	private String zipCode;
	private int partially_vaccinated;
	private int fully_vaccinated;
	private String time;
	
	public Covid(String zipCode, int partially_vaccinated, int fully_vaccinated,String time) {
		
		this.zipCode = zipCode;
		this.fully_vaccinated = fully_vaccinated;
		this.partially_vaccinated = partially_vaccinated;
		this.time = time;
		
	}

	public String getZipCode() {
		return zipCode;
	}

	public int getPartially_vaccinated() {
		return partially_vaccinated;
	}

	public int getFully_vaccinated() {
		return fully_vaccinated;
	}

	public String getTime() {
		return time;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public void setPartially_vaccinated(int partially_vaccinated) {
		this.partially_vaccinated = partially_vaccinated;
	}

	public void setFully_vaccinated(int fully_vaccinated) {
		this.fully_vaccinated = fully_vaccinated;
	}


}
