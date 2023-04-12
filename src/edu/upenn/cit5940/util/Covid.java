package edu.upenn.cit5940.util;

public class Covid {
    private int zipCode;
    private int negativeTests;
    private int positiveTests;
    private int hospitalized;
    private int deaths;
    private int partiallyVaccinated;
    private int fullyVaccinated;
    private int boosted;
    private String timestamp;

    public Covid(int zipCode, int negativeTests, int positiveTests, int hospitalized, int deaths, int partiallyVaccinated, int fullyVaccinated,int boosted,String timestamp) {
        this.zipCode = zipCode;
        this.negativeTests = negativeTests;
        this.positiveTests = positiveTests;
        this.hospitalized = hospitalized;
        this.deaths = deaths;
        this.partiallyVaccinated = partiallyVaccinated;
        this.fullyVaccinated = fullyVaccinated;
        this.boosted = boosted;
        this.timestamp = timestamp;
    }

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	public int getNegativeTests() {
		return negativeTests;
	}

	public void setNegativeTests(int negativeTests) {
		this.negativeTests = negativeTests;
	}

	public int getPositiveTests() {
		return positiveTests;
	}

	public void setPositiveTests(int positiveTests) {
		this.positiveTests = positiveTests;
	}

	public int getHospitalized() {
		return hospitalized;
	}

	public void setHospitalized(int hospitalized) {
		this.hospitalized = hospitalized;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public int getPartiallyVaccinated() {
		return partiallyVaccinated;
	}

	public void setPartiallyVaccinated(int partiallyVaccinated) {
		this.partiallyVaccinated = partiallyVaccinated;
	}

	public int getFullyVaccinated() {
		return fullyVaccinated;
	}

	public void setFullyVaccinated(int fullyVaccinated) {
		this.fullyVaccinated = fullyVaccinated;
	}

	public int getBoosted() {
		return boosted;
	}

	public void setBoosted(int boosted) {
		this.boosted = boosted;
	}
	
	public String getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	 @Override
    public String toString() {
        return "Covid{" +
                "zipCode=" + zipCode +
                ", negativeTests=" + negativeTests +
                ", positiveTests=" + positiveTests +
                ", hospitalized=" + hospitalized +
                ", deaths=" + deaths +
                ", partiallyVaccinated=" + partiallyVaccinated +
                ", fullyVaccinated=" + fullyVaccinated +
                ", boosted=" + boosted +
                ", timestamp=" + timestamp +
                '}'+"\n";
	    }
}
