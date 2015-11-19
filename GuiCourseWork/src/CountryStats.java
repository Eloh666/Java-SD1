
public class CountryStats {
	
	private String countryName;
	private Integer lastNode;
	private Float currentDelta;
	private Integer entriesNumber;
	private Float realIncrease;
	
	public CountryStats(String countryName, Integer lastNode, Float currentDelta) {
		this.countryName = countryName;
		this.lastNode = lastNode;
		this.currentDelta = currentDelta;
		this.entriesNumber = 0;
		this.setRealIncrease(0f);
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Integer getLastNode() {
		return lastNode;
	}

	public void setLastNode(Integer lastNode) {
		this.lastNode = lastNode;
	}

	public Float getCurrentDelta() {
		return currentDelta;
	}

	public void setCurrentDelta(Float currentDelta) {
		this.currentDelta = currentDelta;
	}

	public Integer getEntriesNumber() {
		return entriesNumber;
	}

	public void setEntriesNumber(Integer entriesNumber) {
		this.entriesNumber = entriesNumber;
	}
	
	public Float getRealIncrease() {
		return realIncrease;
	}

	public void setRealIncrease(Float realIncrease) {
		this.realIncrease = realIncrease;
	}

	public void addNewEntries(Integer value)
	{
		this.currentDelta += value - this.lastNode;
		this.lastNode = value;
		this.entriesNumber++;
	}
	
	public void fixAverage()
	{
		if(entriesNumber != 0)
			this.setRealIncrease(this.currentDelta/this.entriesNumber);
		else
			this.setRealIncrease(this.currentDelta);
	}
		
}