package edu.upenn.cit5940.datamanagement;

import edu.upenn.cit5940.util.Zipcode;
import java.util.List;

public interface CovidDataReader {
	
	 List<Zipcode> getCovidData();

}
