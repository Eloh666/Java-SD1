import java.time.LocalDate;

public class Week {
	
	//TODO check local date
	
	private LocalDate startDate;
	private LocalDate endDate;
	private LanguagesTab langTab;

	public Week(String raw) 
	{
		String tabSplit[];
		String spaceSplit[];
		tabSplit = raw.split("\t");
		this.langTab = new LanguagesTab();
		for(Integer i = 1; i < 6; i++)
			langTab.getlanguagesList().add(Integer.parseInt(tabSplit[i]));
		
		spaceSplit = tabSplit[0].split(" ");
		
		startDate = LocalDate.parse(spaceSplit[0]);
		endDate = LocalDate.parse(spaceSplit[2]);
	}
	
	public String toString()         // not really great, but at least it returns the text 
	{								// in even columns as it was provided
		
		
		return this.startDate +" "
				+ this.endDate +" "
				+ this.getLangTab().getJava()+" "
				+ this.getLangTab().getCpp()+" "
				+ this.getLangTab().getCs()+" "
				+ this.getLangTab().getPy()+" "
				+ this.getLangTab().getJs()+" ";
	}

	public LanguagesTab getLangTab() {
		return langTab;
	}

	public void setLangTab(LanguagesTab langTab) {
		this.langTab = langTab;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
		

}