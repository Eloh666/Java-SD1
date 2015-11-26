
public class Week {
	
	private Integer startingYear;
	private Integer endingYear;
	private Integer startingMonth;
	private Integer endingMonth;
	private Integer startingDay;
	private Integer endingDay;
	private LanguagesTab langTab;
	
	public Week(Integer startingYear, Integer endingYear, 
				Integer startingMonth, Integer endingMonth,
				Integer startingDay, Integer endingDay,
				LanguagesTab langTab) 
	{
		this.startingYear = startingYear;
		this.endingYear = endingYear;
		this.startingMonth = startingMonth;
		this.endingMonth = endingMonth;
		this.startingDay = startingDay;
		this.endingDay = endingDay;
		this.langTab = langTab;
	}
	
	public Week(String raw) 
	{
		String tabSplit[];
		String dashSplit[];
		tabSplit = raw.split("\t");
		this.langTab = new LanguagesTab();
		for(Integer i = 1; i < 6; i++)
			langTab.getlanguagesList().add(Integer.parseInt(tabSplit[i]));        
		dashSplit = tabSplit[0].split("-");
		for(int i = 0; i < 6 ; i++)
		{
			dashSplit[i] = dashSplit[i].trim();
			if(dashSplit[i].substring(0,1).equals("0"))
				dashSplit[i] = dashSplit[i].substring(1);
				
		}
		
		// runs on O(N), linear in N where N is the amount of characters of a line (worst case)
		// if they were to be all tabs or dashes, in the real data there are only around 10 iteration per week
		
		this.startingYear = Integer.parseInt(dashSplit[0]);
		this.endingYear = Integer.parseInt(dashSplit[3]);
		this.startingMonth = Integer.parseInt(dashSplit[1]);
		this.endingMonth = Integer.parseInt(dashSplit[4]);
		this.startingDay = Integer.parseInt(dashSplit[2]);
		this.endingDay = Integer.parseInt(dashSplit[5]);
	}
	
	public String toString()         // not really great, but at least it returns the text 
	{								// in even columns as it was provided
		
		
		return String.format("%d-%02d-%02d   %d-%02d-%02d\t %d   %d   %d   %d   %d%n", 
				this.startingYear,this.startingMonth,this.startingDay,
				this.getEndingYear(),this.endingMonth,this.endingDay,
				langTab.getJava(),
				langTab.getCpp(),
				langTab.getCs(),
				langTab.getPy(),
				langTab.getJs());
	}

	public Integer getStartingYear() {
		return startingYear;
	}

	public void setStartingYear(Integer startingYear) {
		this.startingYear = startingYear;
	}

	public Integer getEndingYear() {
		return endingYear;
	}

	public void setEndingYear(Integer endingYear) {
		this.endingYear = endingYear;
	}

	public Integer getStartingMonth() {
		return startingMonth;
	}

	public void setStartingMonth(Integer startingMonth) {
		this.startingMonth = startingMonth;
	}

	public Integer getEndingMonth() {
		return endingMonth;
	}

	public void setEndingMonth(Integer endingMonth) {
		this.endingMonth = endingMonth;
	}

	public Integer getStartingDay() {
		return startingDay;
	}

	public void setStartingDay(Integer startingDay) {
		this.startingDay = startingDay;
	}

	public Integer getEndingDay() {
		return endingDay;
	}

	public void setEndingDay(Integer endingDay) {
		this.endingDay = endingDay;
	}

	public LanguagesTab getLangTab() {
		return langTab;
	}

	public void setLangTab(LanguagesTab langTab) {
		this.langTab = langTab;
	}
		

}