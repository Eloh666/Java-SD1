import java.util.ArrayList;

/*
 * Base class to store the YYYY.txt files
 * It holds the value of the year as an integer (fx 2005)
 * and an ArrayList of countries. Some extra useful methods are also available.
 */

public class Year {
	
	private Integer yearValue;              // slight repetition considering I'm using the year in a TreeMap, but I might need it
	private ArrayList<Country> countries;
	
	public Year(Integer yearValue)
	{
		this.yearValue = yearValue;
		this.countries = new ArrayList<Country>();
	}

	public Integer getYearValue() {
		return yearValue;
	}

	public void setYearValue(Integer yearValue) {
		this.yearValue = yearValue;
	}

	public ArrayList<Country> getCountries() {
		return countries;
	}

	public void setCountries(ArrayList<Country> countries) {
		this.countries = countries;
	}
	
	public void addCountry(String country)
	{
		// could have been done by year.get(i).getCountries().add(new Country(inputString));
		// from the parser class, but I guess doing it like this is a bit cleaner
		countries.add(new Country(country));
	}
	
	@Override
	public String toString()  // simple to string method override
	{
		String temp = new String();
		for(Country cntry : countries)
		{
			temp = temp+= "\n"+cntry.getCountryName()+"\t"+cntry.getLangTab().toString();
		}			
		return temp;
	}
}
