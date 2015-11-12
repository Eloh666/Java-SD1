import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.TreeMap;

public class Parser {

	public static void main(String[] args) throws Exception  {
		
		ArrayList<Week> weeksTab = new ArrayList<Week>();
		TreeMap<Integer,Year> yearsTab = new TreeMap<Integer,Year>();
		
		weeksTab = parseWeeks(weeksTab);
		yearsTab = parseYears(yearsTab);
		
		System.out.println(weeksTab.get(1));
		System.out.println(yearsTab.get(2004).findCountry("Italy"));
		
		question12(weeksTab);
	}
	
	// Question 12 by weeks
	public static String question12(ArrayList<Week> weeks)
	{
		Integer secondIndex = 0;
		Integer interestLoss = 0;
		Week startWeek = null;
		Week endWeek = null;
		Integer language = -1;
		for(Integer firstIndex = 51; firstIndex < weeks.size(); firstIndex++)
		{
			for(Integer i = 0; i < weeks.get(firstIndex).getLangTab().getlanguagesList().size(); i++)
			{
				if(interestLoss < weeks.get(secondIndex).getLangTab().getlanguagesList().get(i) -
						   weeks.get(firstIndex).getLangTab().getlanguagesList().get(i))
				{
					interestLoss = weeks.get(secondIndex).getLangTab().getlanguagesList().get(i) -
							   weeks.get(firstIndex).getLangTab().getlanguagesList().get(i);
					startWeek = weeks.get(firstIndex);
					endWeek = weeks.get(secondIndex);
					language = i;
				}
			}
			secondIndex++;
		}
		System.out.println("Highest Interest Drop: "+startWeek.getLangTab().langFromNumber(language));
		System.out.println("The delta between the start and the end of the timespan is "+interestLoss);
		System.out.println(startWeek);
		System.out.println(endWeek);
		return null;
	}
	
	
	//Parses the iot.txt file and returns a the values into the weeks variableTab
	public static ArrayList<Week> parseWeeks(ArrayList<Week> weeks) throws Exception
	{
		BufferedReader fh = new BufferedReader(new FileReader("iot.txt"));
		// Get rid of the first line
		String reader = fh.readLine();
		while((reader = fh.readLine())!= null)
		{
			weeks.add(new Week(reader));
		}
		fh.close();
		return weeks;
	}
	
	// parses the files from 2004 to 2015 and returns he structure into yearsTab
	public static TreeMap<Integer,Year> parseYears(TreeMap<Integer,Year> years) throws Exception	
	{
		for(Integer i = 2004; i < 2016;i++)
		{
			BufferedReader fh = new BufferedReader(new FileReader(i+".txt"));
			// Get rid of the first line
			String yearsReader = fh.readLine();
			years.put(i,new Year(i));
			while((yearsReader = fh.readLine()) != null)
			{
				years.get(i).addCountry(yearsReader);
			}			
			fh.close();
		}
		return years;
	}

}

