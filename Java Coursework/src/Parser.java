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

