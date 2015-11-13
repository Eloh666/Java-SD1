import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeMap;

import org.omg.Messaging.SyncScopeHelper;

public class Parser {

	public static void main(String[] args) throws Exception  {
		
		ArrayList<Week> weeksTab = new ArrayList<Week>();
		TreeMap<Integer,Year> yearsTab = new TreeMap<Integer,Year>();
		
		weeksTab = parseWeeks(weeksTab);
		yearsTab = parseYears(yearsTab);

		question12b(weeksTab);
	}
	
	// Question 12 hardmode
	public static String question12b(ArrayList<Week> weeks)
	{
		TreeMap<Integer,TreeMap<Integer,AuxMonthsBinder>> auxStructure = new TreeMap<Integer,TreeMap<Integer,AuxMonthsBinder>>();
		TreeMap<Integer,AuxMonthsBinder> temporaryBinder;
		for(Integer i = 2004; i<2016 ;i++)
		{
			temporaryBinder = new TreeMap<Integer,AuxMonthsBinder>();
			for(Integer j = 1; j<=12 ;j++)
			{
				temporaryBinder.put(j, new AuxMonthsBinder());
			}
			auxStructure.put(i, temporaryBinder);
		}
		
		for(Week curWeek : weeks)                     // TODO better weeks
		{
			if(curWeek.getStartingMonth() == curWeek.getEndingMonth())
			{
				Integer temp[] = {0,0,0,0,0};
				for(Integer i = 0; i<5; i++)
				{
					temp[i] += curWeek.getLangTab().getlanguagesList().get(i);
				}
				auxStructure.get(curWeek.getStartingYear()).get(curWeek.getStartingMonth()).addWeeks(1);
				auxStructure.get(curWeek.getStartingYear()).get(curWeek.getStartingMonth()).getValue().addAll(Arrays.asList(temp));
			}
			
			else
			{
				Integer temp[] = {0,0,0,0,0};
				for(Integer i = 0; i<5; i++)
				{
					temp[i] += curWeek.getLangTab().getlanguagesList().get(i)/2;
				}
				auxStructure.get(curWeek.getStartingYear()).get(curWeek.getStartingMonth()).addWeeks(0.5f);
				auxStructure.get(curWeek.getStartingYear()).get(curWeek.getStartingMonth()).getValue().addAll(Arrays.asList(temp));
				
				Integer temp2[] = {0,0,0,0,0};
				for(Integer i = 0; i<5; i++)
				{
					temp2[i] += curWeek.getLangTab().getlanguagesList().get(i)/2;
				}
				auxStructure.get(curWeek.getEndingYear()).get(curWeek.getEndingMonth()).addWeeks(0.5f);
				auxStructure.get(curWeek.getEndingYear()).get(curWeek.getEndingMonth()).getValue().addAll(Arrays.asList(temp2));
			}
		}
		
		ArrayList<Float[]> auxArray = new ArrayList<Float[]>();
		for(Integer i = 2004; i < 2016; i++)
		{
			for(Integer j = 1; j <= 12; j++)
			{
				if(auxStructure.get(i).get(j).getWeeks() != 0)
				{
					System.out.println(auxStructure.get(i).get(j).getWeeks());
					auxArray.add(auxStructure.get(i).get(j).getAverage());
				}
					
			}
		}
		
		Float max[] = {0f,0f,0f,0f,0f};
		Integer maxIndexes[] = {-1,-1,-1,-1,-1};
		for(Float[] f:auxArray)
		{
			//TODO from 0 to 12 etc
		}
		
		
		
		return null;
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
		return "Highest Interest Drop: "+startWeek.getLangTab().langFromNumber(language)
				+ "\nThe delta between the start and the end of the timespan is "
				+ interestLoss + "\nWeek 1: " + endWeek + "\nWeek 2: " + startWeek;
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

