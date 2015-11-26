import java.util.ArrayList;


/*
 * The class basically consists in an 
 * array of integer and a few related methods. 
 * This could have been avoidable, but in the original design 
 * and in general for safety I felt like 
 * it might have been useful to have more freedom if needed.
 */

public class LanguagesTab {
	
	private ArrayList<Integer> languagesList;
	
	public LanguagesTab()
	{
		this.languagesList = new ArrayList<Integer>();
	}
	
	public ArrayList<Integer> getlanguagesList() {
		return languagesList;
	}

	public void setlanguagesList(ArrayList<Integer> languagesList) {
		this.languagesList = languagesList;
	}
	
	public Integer getJava()
	{
		if(languagesList.get(0) != null)
			return languagesList.get(0);
		else
			return -1;
	}
	
	public Integer getCpp()
	{
		if(languagesList.get(1) != null)
			return languagesList.get(1);
		else
			return -1;
	}
	
	public Integer getCs()
	{
		if(languagesList.get(2) != null)
			return languagesList.get(2);
		else
			return -1;
	}
	
	public Integer getPy()
	{
		if(languagesList.get(3) != null)
			return languagesList.get(3);
		else
			return -1;
	}
	
	public Integer getJs()
	{
		if(languagesList.get(4) != null)
			return languagesList.get(4);
		else
			return -1;
	}
	
	public String toString()
	{
		String temp = new String();
		for(Integer i: languagesList)               // runs on O(N), linear in N, where n is the nuber of countries
		{                                             // defenatly not as good as searching over hashtables and trees but
			temp +="\t" + i;                            // it allows me to keep countries in an ArrayList for easy iteration
		}
		return temp;
	}
}
