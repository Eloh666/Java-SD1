import java.util.ArrayList;

public class Year {
	
	private Integer yearValue;              // slight repetition considering I'm using the year in a treemap, but I might need it
	private ArrayList<Country> countries;    // bash me if I forget to delete the comment
	
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
	
	public String toString()
	{
		String temp = new String();
		for(Country cntry : countries)
		{
			temp = temp+= "\n"+cntry.getCountryName()+"\t"+cntry.getLangTab().toString();
		}			
		return temp;
	}
	
	public Country findCountry(String key)
	{
		Boolean found = false;
		Country dummy = null;
		for(Integer i = 0; i < countries.size() && !found; i++)
		{
			if(countries.get(i).getCountryName().equals(key))
			{
				found = true;
				dummy = countries.get(i);
			}
		}
		return dummy;
		
	}

}
