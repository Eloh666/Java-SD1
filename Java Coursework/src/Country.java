
public class Country {
	
	private String countryName;
	private LanguagesTab langTab;
	
	public Country(String raw)
	{
		this.langTab = new LanguagesTab();	
		
		String lineSplit[] = raw.split("\t");
		this.countryName = lineSplit[0];
		for(Integer i = 1; i < 6; i++)
			langTab.getlanguagesList().add(Integer.parseInt(lineSplit[i]));
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public LanguagesTab getLangTab() {
		return langTab;
	}

	public void setLangTab(LanguagesTab langTab) {
		this.langTab = langTab;
	}
	
	public String toString()
	{
		return countryName + langTab.toString();
	}

}
