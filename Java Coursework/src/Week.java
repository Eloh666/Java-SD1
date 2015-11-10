
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
		this.endingYear = Integer.parseInt(dashSplit[1]);
		this.startingMonth = Integer.parseInt(dashSplit[2]);
		this.endingMonth = Integer.parseInt(dashSplit[3]);
		this.startingDay = Integer.parseInt(dashSplit[4]);
		this.endingDay = Integer.parseInt(dashSplit[5]);
	}
	
	public String toString()
	{
		return startingYear+"-"+startingMonth+"-"+startingDay
			   + " - "+endingYear+"-"+endingMonth+"-"+endingDay  //TODO fix spacing
			   + "	"+langTab.getJava()
			   +"	"+langTab.getCpp()
			   +"	"+langTab.getCs()
			   +"	"+langTab.getPy()
			   +"	"+langTab.getJs();
	}
		

}
