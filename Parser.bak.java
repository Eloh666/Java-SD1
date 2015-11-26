import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeMap;



public class Parser {
	
	private ArrayList<Week> weeksTab = new ArrayList<Week>();
	private TreeMap<Integer,Year> yearsTab = new TreeMap<Integer,Year>();
	private Integer sY;
	private Integer eY;

	public Parser(Integer sY, Integer eY) throws Exception  {
		this.sY = sY;
		this.eY = eY;
		ArrayList<Week> weeksTab = new ArrayList<Week>();
		TreeMap<Integer,Year> yearsTab = new TreeMap<Integer,Year>();
		
		this.weeksTab = parseWeeks(weeksTab);
		this.yearsTab = parseYears(yearsTab,sY,eY);
	}
	
	public String question11()
	{
		TreeMap<String, CountryStats> mappedCountries = new TreeMap<String, CountryStats>();
		ArrayList<CountryStats> countrySortable = new ArrayList<CountryStats>();
		CountryStats tempStorage;
		CompTool comparator = new CompTool();
		String answer = "The 5 countries who showed that showed the highest interested\nin programming languages over the year are the following: \n\n"
				+ 		"Country\t\tAverage Increase per Year\n\n";
		
		for(Integer i = sY; i < eY; i++)
		{
			for(Country country: yearsTab.get(i).getCountries())
			{
				if(!mappedCountries.containsKey(country.getCountryName()))
				{
					tempStorage = new CountryStats(country.getCountryName(),
							country.getLangTab().getlanguagesList().stream().mapToInt(value -> value).sum(),
							0f);
					// That's so cool, didn't know I could do that!
					
					mappedCountries.put(country.getCountryName(),tempStorage);
				}
				else
				{
					mappedCountries.get(country.getCountryName()).addNewEntries(country.getLangTab().getlanguagesList().stream().mapToInt(value -> value).sum());					
				}
			}
		}
		
		countrySortable.addAll(mappedCountries.values());
		for(CountryStats cs: countrySortable)
			cs.fixAverage();
		Collections.sort(countrySortable, comparator);
		for(int i = 0; i < 5; i++)
			answer += String.format("%-40s %-20f\n",
					countrySortable.get(i).getCountryName(),countrySortable.get(i).getRealIncrease());
		
			return answer;
	}
	
	// Question 12 hard mode version
	public String question12()
	{
		TreeMap<Integer,TreeMap<Integer,AuxMonthsBinder>> auxStructure = transformWeeks();
		
		Integer langIdent = -1;
		Float biggestDrop = 0f;
		Integer dropMonth = -1;
		Integer dropYear = -1;
		Float[] valuesCur = new Float[5];
		Float[] valuesNext = new Float[5];
		Float currentValue = 0f;
		Float nextValue = 0f;
		for(Integer i = sY; i < eY; i++)
		{
			for(Integer j = 1; j <= 12; j++)
			{
				if((i == 2004 && j == 1) || (i == 2015 && j == 8))   // skip month if data set != complete
					j++;
				if(auxStructure.get(i+1).get(j).getWeeks() != 0f)
				{
					valuesCur = auxStructure.get(i).get(j).getAverage();
					valuesNext = auxStructure.get(i+1).get(j).getAverage();
					for(Integer k = 0; k <5; k++ )
					{
						if(valuesCur[k] - valuesNext[k] > biggestDrop)
						{
							currentValue = valuesCur[k];
							nextValue = valuesNext[k];
							biggestDrop = valuesCur[k] - valuesNext[k];
							dropMonth = j;
							dropYear = i;
							langIdent = k;
						}
					}
				}
					
			}
		}		
		return "The biggest drop happened for "+decodeLanguage(langIdent)+"\n between "
						+ dropYear+" and "+(dropYear+1)+" during "+ decodeMonth(dropMonth)+"." +
				"\nThe drop in usage was "+biggestDrop+". \nThe average "
						+ "monthly usage went down from "+currentValue+" to "+nextValue+".";
	}

	// Question 13
	public String question13()
	{
		TreeMap<Integer,TreeMap<Integer,AuxMonthsBinder>> auxStructure = transformWeeks();
		String answer = "The usage statistics for java between between September/October"
				+ " and \nthe rest of the academic year for the given dataset are following: \n\n"
				+ "Academic Year       Sept/October       Full Year            Delta\n";
		
		
		for(Integer i = sY; i < eY ; i++)
		{
			Float averageAcYear = 0f;
			Float averageSeptOct = 0f;
			Integer counterAcYear = 0;
			
			averageSeptOct =(auxStructure.get(i).get(9).getValueForLanguage(0)
							+ auxStructure.get(i).get(10).getValueForLanguage(0))/61;
			
			for(Integer j = 1; j < 12; j++)
			{
				if(j >= 9)
				{
					averageAcYear += auxStructure.get(i).get(j).getValueForLanguage(0);
					counterAcYear += auxStructure.get(i).get(j).getWeeks();
				}
				
				if(auxStructure.get(i).get(j).getWeeks() != 0)
				{
					if(j <= 8)
					{
						averageAcYear += auxStructure.get(i+1).get(j).getValueForLanguage(0);
						counterAcYear += auxStructure.get(i).get(j).getWeeks();
					}
				}
			}
			answer += String.format("%d/%-10d         %-10f         %-10f         %-10f\n", i,i+1,
					averageSeptOct,averageAcYear/counterAcYear,(averageSeptOct - averageAcYear/counterAcYear));
		}
		return answer;
	}
	
	public String question14()
	{
		TreeMap<String, Double> sigma = new TreeMap<String, Double>();
		Double maxCorrelation = -1d;
		Integer xLanguage = -1;
		Integer yLanguage = -1;
		Double tempValue = 0d;
		
		for(Week week: weeksTab)
		{
			for(Integer i = 0; i < 5; i++)          //TODO putIfAbsent
			{
				if(sigma.containsKey(i.toString()))
				{
					sigma.replace(i.toString(),
							sigma.get(i.toString())+week.getLangTab().getlanguagesList().get(i));
					
					sigma.replace(i.toString() + i.toString(),
							sigma.get(i.toString() + i.toString())+Math.pow(week.getLangTab().getlanguagesList().get(i), 2));
				}
				else
				{
					sigma.put(i.toString(),
							(double) week.getLangTab().getlanguagesList().get(i));
					
					sigma.put(i.toString() + i.toString(),
							Math.pow(week.getLangTab().getlanguagesList().get(i), 2));
				}
				for(Integer j = i+1; j < 5; j++)
				{
					if(sigma.containsKey(i.toString() + j.toString()))
						sigma.replace(i.toString() + j.toString(),
								sigma.get(i.toString() + j.toString()) 
								+ week.getLangTab().getlanguagesList().get(i) 
								* week.getLangTab().getlanguagesList().get(j));
					else
						sigma.put(i.toString() + j.toString(), 
								(double) week.getLangTab().getlanguagesList().get(i) 
								* week.getLangTab().getlanguagesList().get(j));
				}
			}
		}
		
		for(Integer i = 0; i < 5; i++)
		{
			for(Integer j = i+1; j < 5; j++)
			{
				tempValue = getR(sigma.get(i.toString()), sigma.get(i.toString()+i.toString()),
							     sigma.get(j.toString()), sigma.get(j.toString()+j.toString()),
							 	 sigma.get(i.toString()+j.toString()));
				
				if(maxCorrelation < tempValue)
				{
					maxCorrelation = tempValue;
					xLanguage = i;
					yLanguage = j;
				}
			}
				
		}
		
		return "The highest correlation can be found between"+decodeLanguage(xLanguage)
				+ " and "+decodeLanguage(yLanguage)
				+ "with a product moment coeffienct r of "+maxCorrelation;
	}
	
	// Question 15
	public String question15()
	{
		Integer head = new Integer(0);
		Integer tail = new Integer(0);
		Integer value = new Integer(0);
		Integer language = new Integer(0);
		Integer tempHead[] = {0,0,0,0,0};
		Integer tempTail[] = {0,0,0,0,0};
		Integer tempValue[] = {0,0,0,0,0};
		String answer = "The deepest decline is found for ";
		
		for(Integer i = 1; i< weeksTab.size(); i++)
		{
			for(Integer j = 0; j<5; j++)
			{
				if(weeksTab.get(i-1).getLangTab().getlanguagesList().get(j) >=	weeksTab.get(i).getLangTab().getlanguagesList().get(j))
				{
					tempValue[j] += (weeksTab.get(i-1).getLangTab().getlanguagesList().get(j) - weeksTab.get(i).getLangTab().getlanguagesList().get(j));
					tempTail[j] = i;
					if(tempValue[j] > value)
					{
						value = tempValue[j];
						head = tempHead[j];
						tail = tempTail[j];
					}
				}
				else
				{
					tempHead[j] = i;
					tempTail[j] = i;
					tempValue[j] = 0;
				}
			}
		}

		answer += decodeLanguage(language)+".\nThe timespam for the decline is the following:\n\n";
		for(Integer j = head; j<= tail; j++)
			answer += weeksTab.get(j).toString();
		return answer;
	}
	
	//Creates an auxiliary data structures to easily access months based on the weeks IOT file
	private TreeMap<Integer,TreeMap<Integer,AuxMonthsBinder>>  transformWeeks()
	{
		TreeMap<Integer,TreeMap<Integer,AuxMonthsBinder>> auxStructure = new TreeMap<Integer,TreeMap<Integer,AuxMonthsBinder>>();
		TreeMap<Integer,AuxMonthsBinder> temporaryBinder;
		for(Integer i = sY; i<= eY ;i++)
		{
			temporaryBinder = new TreeMap<Integer,AuxMonthsBinder>();
			for(Integer j = 1; j<=12 ;j++)
			{
				temporaryBinder.put(j, new AuxMonthsBinder());
			}
			auxStructure.put(i, temporaryBinder);
		}
		
		for(Week curWeek : weeksTab)                    
		{
			if(curWeek.getStartingMonth() == curWeek.getEndingMonth())
			{
				Float temp[] = {0f,0f,0f,0f,0f};
				for(Integer i = 0; i<5; i++)
				{
					temp[i] += (float) curWeek.getLangTab().getlanguagesList().get(i)*7;
				}
				auxStructure.get(curWeek.getStartingYear()).get(curWeek.getStartingMonth()).addDays(7);
				auxStructure.get(curWeek.getStartingYear()).get(curWeek.getStartingMonth()).getValue().addAll(Arrays.asList(temp));
			}
			
			else
			{
				Float temp[] = {0f,0f,0f,0f,0f};
				for(Integer i = 0; i<5; i++)
				{
					temp[i] += ((float) curWeek.getLangTab().getlanguagesList().get(i))*(7-curWeek.getEndingDay());
				}
				auxStructure.get(curWeek.getStartingYear()).get(curWeek.getStartingMonth()).addDays(7-curWeek.getEndingDay());
				auxStructure.get(curWeek.getStartingYear()).get(curWeek.getStartingMonth()).getValue().addAll(Arrays.asList(temp));
				
				Float temp2[] = {0f,0f,0f,0f,0f};
				for(Integer i = 0; i<5; i++)
				{
					temp2[i] += ((float) curWeek.getLangTab().getlanguagesList().get(i))*curWeek.getEndingDay();
				}
				auxStructure.get(curWeek.getEndingYear()).get(curWeek.getEndingMonth()).addDays(curWeek.getEndingDay());
				auxStructure.get(curWeek.getEndingYear()).get(curWeek.getEndingMonth()).getValue().addAll(Arrays.asList(temp2));
			}
		}
		return auxStructure;
	}
	
	private String decodeLanguage(Integer value)
	{
		if(value == 0)
			return "Java";
		if(value == 1)
			return "C++";
		if(value == 2)
			return "C#";
		if(value == 3)
			return "Python";
		if(value == 4)
			return "JavaScript";
		else
			return "Not found";		
	}
	
	private String decodeMonth (Integer value)
	{
		if(value == 1)
			return "January";
		if(value == 2)
			return "Februrary";
		if(value == 3)
			return "March";
		if(value == 4)
			return "April";
		if(value == 5)
			return "May";
		if(value == 6)
			return "June";
		if(value == 7)
			return "July";
		if(value == 8)
			return "August";
		if(value == 9)
			return "September";
		if(value == 10)
			return "October";
		if(value == 11)
			return "November";
		if(value == 12)
			return "December";
		else
			return "Not found";		
	}
	
	//Parses the iot.txt file and returns a the values into the weeks variableTab
	private ArrayList<Week> parseWeeks(ArrayList<Week> weeks) throws Exception
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
	private TreeMap<Integer,Year> parseYears(TreeMap<Integer,Year> years, Integer sY, Integer eY) throws Exception	
	{
		for(Integer i = sY; i <= eY; i++)
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

	private Double getR(Double x, Double xx, Double y, Double yy, Double xy)
	{
		Double SigmaXY = 0d;
		Double SigmaXX = 0d;
		Double SigmaYY = 0d;
		
		SigmaXX = xx - ((x*x)/weeksTab.size());
		SigmaYY = yy - ((y*y)/weeksTab.size());
		SigmaXY = xy - ((x*y)/weeksTab.size());

		return (SigmaXY / (Math.sqrt(SigmaXX*SigmaYY)));
	}
	
}
