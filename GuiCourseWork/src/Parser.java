import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;



	/* The class providing the actual computation
	*  it will be instantiated by the "CourseWork2 class which
	*  serves as actual main for the project. 
	*  The only purpose in separating the two is making everything a bit more clean.
	*/

public class Parser {
	
	// The iot.txt file is held in a ArrayList of weeks
	private ArrayList<Week> weeksTab = new ArrayList<Week>();
	// The year files (20xx.txt) are being held in a treeMap of Integers/Years
	private TreeMap<Integer,Year> yearsTab = new TreeMap<Integer,Year>();
	
	// The starting/ending year, so that the application can be used for different years
	private Integer sY;
	private Integer eY;

	/*
	 * Simply Constructor that takes the starting and ending year when called.
	 * Prepares the weeksTab and yearsTab structures so that can later 
	 * be used by the questions methods
	 */
	
	public Parser(Integer sY, Integer eY) throws Exception  {
		this.sY = sY;
		this.eY = eY;
		ArrayList<Week> weeksTab = new ArrayList<Week>();
		TreeMap<Integer,Year> yearsTab = new TreeMap<Integer,Year>();
		
		// Parses iot.txt
		this.weeksTab = parseWeeks(weeksTab);
		// Parses the year files
		this.yearsTab = parseYears(yearsTab,sY,eY);
	}
	
	
	/*
	 *  Simple method that returns a string "answer" for question 11.
	 *  I will provide more information regarding my choices for
	 *  solving the problem in the word document.
	 */
	public String question11()
	{
		// Creates a Map to store the information for the various countries
		Map<String, IncreaseDetails> mappedCountries = new LinkedHashMap<>();
		/*
		 * A small start/stub for the answer string that will later be displayed 
		 * by the application if the button "question11" is pressed. 
		 */
		String answer = "The countries who showed the highest increase in interest "
						+ "towards programming languages are the following: \n\n";
		/*
		 *  The loop runs takes goes through each year, and each country
		 *  for that specific year extracting the summation values for the languages.
		 *  The values are stored in the custom structure "mappedCountries".
		 *  Runs in O(N*M) linear towards: 
		 *  M = Number of years
		 *  N = Number of countries (it can very from year to year)
		 */
		for(Integer i = sY; i <= eY; i++)
		{
			for(Country country: yearsTab.get(i).getCountries())
			{
				/*
				 * Adds the country if absent and initialises it's starting value.
				 * Given the formula for growth:
				 * G = ((B-A)/A) * 100
				 * 
				 * It sets A to the sum of the language tab for the country occurrence
				 * and B to -1;
				 */
				mappedCountries.putIfAbsent(country.getCountryName(), 
						new IncreaseDetails((float) country.getLangTab()
								.getlanguagesList()
								.stream()
								.mapToInt(value -> value).sum(), -1f));
				
				/*
				 * Updated the new value in order to calculate the growth.
				 * B is set to the sum of the language for the occurrence of the country;
				 * If the country only appears once, the increase will be 0.
				 */
				mappedCountries.get(country.getCountryName())
								.setSecondValue( (float) country.getLangTab()
								.getlanguagesList()
								.stream()
								.mapToInt(value -> value).sum());
				
				/*
				 * Calculates a new delta for the growth in each iteration.
				 * The extra calculations avoid looping again through the countries
				 */
				mappedCountries.get(country.getCountryName()).fixAverage();
			}
		}
		
		/*
		 * Calls the function to sort the map,
		 * Using the stream() api runs (probably) in O(N)
		 */
		mappedCountries = sortMap(mappedCountries);
		
		/*
		 * Prints the first 5 values of the map.
		 * Way more neater than a for loop, streams are awesome!
		 */
		
		answer += mappedCountries.entrySet()
				.stream()
				.limit(5)
				.map(s -> String.format("%-20s%-2.2f",s.getKey().toString()
						,s.getValue().getRealIncrease()) )
				.collect(Collectors.joining("\n"));
		
		return answer;
	}
		
	/*
	 *  Return a string with the answer to question 12. 
	 *  I will comment the output and the decision making in
	 *  the word document
	 */
	public String question12()
	{
		/* New custom data structure to properly hold the averages for the months.
		 * the new auxiliar data structure is created by the method "transformWeeks()"
		 */
		TreeMap<Integer,TreeMap<Integer,AuxMonthsBinder>> auxStructure = transformWeeks();
		
		// Counter for identify the language who had the biggest drop
		Integer langIdent = -1;
		// Counter for the drop itself
		Float biggestDrop = 0f;
		// Year and month in which the drop started
		Integer dropMonth = -1;
		Integer dropYear = -1;
		// Temp storages for the drops based on the languages
		Float[] valuesCur = new Float[5];
		Float[] valuesNext = new Float[5];
		Float currentValue = 0f;
		Float nextValue = 0f;
		
		/*
		 * Runs in O(N) where N is the number of months 
		 * present in auxStructure. Therefore about 1/4.35
		 * of the size of the number of lines present in the iot.txt file
		 */
		for(Integer i = sY; i < eY; i++) // iterate for the year
		{
			for(Integer j = 1; j <= 12; j++) // iterate for the month
			{
				// skip month if data set != complete
				if((i == 2004 && j == 1) || (i == 2015 && j == 8))   
					j++;
				/*
				 * Simply updates the values of the counters
				 * when a bigger drop is encountered
				 */
				if(auxStructure.get(i+1).get(j).getDays() != 0f)
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
		// returns the answers with proper values
		return "The biggest drop happened for "+decodeLanguage(langIdent)+"\n between "
						+ dropYear+" and "+(dropYear+1)+" during "+ decodeMonth(dropMonth)+"." 
						+ "\nThe drop in usage was "+biggestDrop+". \nThe average "
						+ "monthly usage went down from "+currentValue+" to "+nextValue+".";
	}

	
	/*
	 *  Return a string with the answer to question 13. 
	 *  I will comment the output and the decision making in
	 *  the word document
	 */
	public String question13()
	{
		/* New custom data structure to properly hold the averages for the months.
		 * the new Auxiliar data structure is created by the method "transformWeeks()"
		 */
		TreeMap<Integer,TreeMap<Integer,AuxMonthsBinder>> auxStructure = transformWeeks();
		
		String answer = "The usage statistics for java between between September/October"
				+ " and \nthe rest of the academic"
				+ " year for the given dataset are following: \n\n"
				+ "Academic Year       Sept/October"
				+ "       Full Year            Delta\n";
		
		/*
		 *  The method calculates for each academic year
		 *  the average for September and October
		 *  and the value for the whole year by using the auxiliar data
		 *  structure. Like the previous question also runs in O(N)
		 */
		for(Integer i = sY; i < eY ; i++)
		{
			// counters for the averages
			Float averageAcYear = 0f;
			Float averageSeptOct = 0f;
			Integer counterAcYear = 0;
			
			/*
			 * Calculates the average for September/October
			 * 61 is hard-coded for length, but can be calculated 
			 * dynamically by pulling the details from the auxiliary structure if needed
			 */
			averageSeptOct =(auxStructure.get(i).get(9)
					.getValueForLanguage(0)
					+ auxStructure.get(i).get(10)
					.getValueForLanguage(0))/61;
			
			/*
			 * Calculates the values for the year
			 */
			for(Integer j = 1; j < 12; j++)
			{
				if(j >= 9)
				{
					averageAcYear += auxStructure
							.get(i).get(j).getValueForLanguage(0);
					counterAcYear += auxStructure
							.get(i).get(j).getDays();
				}
				
				if(auxStructure.get(i).get(j).getDays() != 0)
				{
					if(j <= 8)
					{
						averageAcYear += auxStructure
								.get(i+1).get(j).getValueForLanguage(0);
						counterAcYear += auxStructure
								.get(i).get(j).getDays();
					}
				}
			}
			/*
			 * The answer is updated providing the value of each single
			 * academic year compared to the ones of Sept/October for that year
			 */
			answer += String.format("%d/%-10d         "
					+ "%-10f         "
					+ "%-10f         "
					+ "%-10f\n", i,i+1,
					averageSeptOct
					,averageAcYear/counterAcYear
					,(averageSeptOct - averageAcYear/counterAcYear));
		}
		return answer;
	}
	
	/*
	 * Method for the answer to question 14.
	 */	
	public String question14()
	{
		/*
		 * The TreeMap Sigma will store the temporary results for
		 * the summations used by the Pearon's formula.
		 * The keys are based on how the values for the 
		 * languages are stored inside the weeksTab structure:
		 * For example:
			*	Java : index 0
			*	C++ : Index 1
			*	The keys provided will be:
			*	�0� : the summation for the values of java
			*	�00�: the summation for the values of java squared 
			*	�1� : the summation for the values of C++
			*	�11�: the summation for the values of C++ squared.
		 * Given 5 languages, the size of sigma will always be 20.
		 */
		TreeMap<String, Double> sigma = new TreeMap<String, Double>();
		// The value of the max correlation found
		Double maxCorrelation = -1d;
		// The first and second languages involved
		Integer xLanguage = -1;
		Integer yLanguage = -1;
		// Temporary counter
		Double tempValue = 0d;

		/* 
		 * The loop runs in O(N) where N is size of the weeksTab array
		 * and calculates the summations for each one of the language combinations
		 */
		for(Week week: weeksTab)
		{
			for(Integer i = 0; i < 5; i++)          //TODO putIfAbsent
			{
				/*
				 * Adds the languages to map if not already available with a dummy
				 * value of 0
				 */
				sigma.putIfAbsent(i.toString(),0d);
				sigma.putIfAbsent(i.toString() + i.toString(),0d);
				/* 
				 * Updates the summations for S(x) and S(x,x) - S(y), S(y,y)
				 */
				sigma.replace(i.toString(), 
						sigma.get(i.toString())
						+ week.getLangTab().getlanguagesList().get(i));
				sigma.replace(i.toString() 
						+ i.toString(), sigma.get(i.toString() 
						+ i.toString())+Math.pow(
								week.getLangTab().getlanguagesList().get(i), 2));
				
				for(Integer j = i+1; j < 5; j++)
				{
					/*
					 * Adds the double value correlations
					 * and updates the values for the double language summations S(x,y)
					 */
					sigma.putIfAbsent(i.toString() 
							+ j.toString(),0d);
					sigma.replace(i.toString() + j.toString()
					, sigma.get(i.toString() 
					+ j.toString()) 
					+ week.getLangTab().getlanguagesList().get(i) 
					* week.getLangTab().getlanguagesList().get(j));	
				}
			}
		}
				
		/*
		 * Runs in O(N) where N is the size of the sigma map (a fixed 20 for 5 language)
		 * and calculated the correlation calling the getR method
		 */
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
		
		return "The highest correlation can be found between "+decodeLanguage(xLanguage)
				+ " and "+decodeLanguage(yLanguage)
				+ " with a product moment coeffienct r of "+maxCorrelation;
	}
	
	/*
	 * Provides an answer for question 15.
	 * 
	 */
	public String question15()
	{
		/*
		 * The first 4 counters hold the start date
		 * for the decline, the end date (in the form of position 
		 * in the weeksTab array). The tempXXXX variables are instead
		 *  temporary counters to hold the current values for
		 * each specific language.
		 */
		Integer head = new Integer(0);
		Integer tail = new Integer(0);
		Integer value = new Integer(0);
		Integer language = new Integer(0);
		Integer tempHead[] = {0,0,0,0,0};
		Integer tempTail[] = {0,0,0,0,0};
		Integer tempValue[] = {0,0,0,0,0};
		String answer = "The deepest decline is found for ";
		
		/*
		 * The method goes through all the countries in the weeksTab array
		 * and updating the temporary counters and final ones.
		 * Runs in O(N) where N is the size of the weeksTab Array.
		 */
		
		
		// for each week
		for(Integer i = 1; i< weeksTab.size(); i++)
		{
			// for each language
			for(Integer j = 0; j<5; j++)
			{
				if(weeksTab.get(i-1).getLangTab()
						.getlanguagesList().get(j) >=	
						weeksTab.get(i).getLangTab()
						.getlanguagesList().get(j))
				{
					tempValue[j] += (weeksTab.get(i-1).getLangTab()
							.getlanguagesList().get(j) 
							- weeksTab.get(i).getLangTab()
							.getlanguagesList().get(j));
					
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

		/*
		 * returns the answer of the language which had the deepest
		 * decline and the week span in which it takes place
		 * worst case would be O(N) if one language had a contiguous
		 * decline from week 0 to week N
		 */
		
		answer += decodeLanguage(language)
				+ ".\nThe timespam for the decline is the following:\n\n";
		for(Integer j = head; j<= tail; j++)
			answer += weeksTab.get(j).toString();
		return answer;
	}
	
	/*
	 * Creates an auxiliary data structures to easily 
	 * access months based on the weeks IOT file
	 * I will provide extra information in the word document
	 */
	private TreeMap<Integer,TreeMap<Integer,AuxMonthsBinder>>  transformWeeks()
	{
		/*
		 * TreeMap of Year, linked to a map of Month-AuxMonthsBinder
		 * aka a custom data structure that holds the daily average for that month
		 */
		TreeMap<Integer,TreeMap<Integer,AuxMonthsBinder>> 
		auxStructure = new TreeMap<Integer,TreeMap<Integer,AuxMonthsBinder>>();
		
		//temporary value to easily store values into "auxStructure"
		TreeMap<Integer,AuxMonthsBinder> temporaryBinder;
		
		/*
		 * Initialises the structure for later use.
		 * I could have done this bit in the main loop while actually
		 * adding the data to the new structure with putIfAbset or .contains().
		 * As I personally found the task of splitting the week values in months
		 * slightly more difficult than originally expected I have chosen to
		 * leave a slightly easier, although less efficient/elegant design.
		 * Runs in O(N*12) where and therefore linear to N, the number
		 * of years of the span. 
		 */
		for(Integer i = sY; i<= eY ;i++)
		{
			temporaryBinder = new TreeMap<Integer,AuxMonthsBinder>();
			for(Integer j = 1; j<=12 ;j++)
			{
				temporaryBinder.put(j, new AuxMonthsBinder());
			}
			auxStructure.put(i, temporaryBinder);
		}
		
		/*
		 * Main loop of the method. 
		 * Runs in O(N*12) where and therefore linear to N, the number
		 * of years of the span.
		 */
		for(Week curWeek : weeksTab)                    
		{
			/*
			 * if a week is completely contained into a specific month
			 * adds it's value multiplied by 7 (number of day in a
			 * standard week in the Gregorian calendar to that month.
			 * Also adds 7 days to the appropriate counter.
			 */
			if(curWeek.getStartDate().getMonthValue() 
					== curWeek.getEndDate().getMonthValue())
			{
				Float temp[] = {0f,0f,0f,0f,0f};
				for(Integer i = 0; i<5; i++)
				{
					temp[i] += (float) curWeek.getLangTab()
							.getlanguagesList().get(i)*7;
				}
				auxStructure.get(curWeek.getStartDate()
						.getYear())
						.get(curWeek.getStartDate()
						.getMonthValue()).addDays(7);
				
				auxStructure.get(curWeek.getStartDate()
						.getYear())
						.get(curWeek.getStartDate()
						.getMonthValue()).getValue()
						.addAll(Arrays.asList(temp));
			}
			
			/*
			 * If a week is "split" between two months, the values are 
			 * instead increased by the number of days of each month
			 * and added to that specific node of the map
			 */
			else
			{
				Float temp[] = {0f,0f,0f,0f,0f};
				for(Integer i = 0; i<5; i++)
				{
					temp[i] += ((float) curWeek.getLangTab()
							.getlanguagesList()
							.get(i))*(7-curWeek
							.getEndDate().getDayOfMonth());
				}
				auxStructure.get(curWeek.getStartDate()
						.getYear())
						.get(curWeek.getStartDate()
						.getMonthValue())
						.addDays(7-curWeek.getEndDate().getDayOfMonth());
				
				auxStructure.get(curWeek.getStartDate()
						.getYear())
						.get(curWeek.getStartDate()
						.getMonthValue()).getValue()
						.addAll(Arrays.asList(temp));
				
				Float temp2[] = {0f,0f,0f,0f,0f};
				for(Integer i = 0; i<5; i++)
				{
					temp2[i] += ((float) curWeek.getLangTab()
							.getlanguagesList()
							.get(i))*curWeek.getEndDate()
							.getDayOfMonth();
				}
				auxStructure.get(curWeek.getEndDate()
						.getYear())
						.get(curWeek.getEndDate()
						.getMonthValue())
						.addDays(curWeek.getEndDate().getDayOfMonth());
				
				auxStructure.get(curWeek.getEndDate()
						.getYear())
						.get(curWeek.getEndDate()
						.getMonthValue()).getValue()
						.addAll(Arrays.asList(temp2));
			}
		}
		//returns the newly built structure
		return auxStructure;
	}
	
	/*
	 * simple deconding method that returns
	 * the value of the language based on a given integer
	 * it's only purpose is to return better answers
	 */
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
	
	/*
	 * simple deconding method that returns
	 * the value of the month based on a given integer
	 * it's only
	*/
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
	
	/*
	 * The method, used by question 1 takes an unsortedMap
	 * as input and sorts it according to the value of the
	 * value of the "realIncrease" field.
	 * Returns then a new sorted LinkedHashMap.
	 * I will try to provide more information in the word document.
	 */
	private Map<String, IncreaseDetails> sortMap(Map<String, IncreaseDetails> unsortedMap)
	{
		Map<String, IncreaseDetails> sortedMap = unsortedMap.entrySet()
				.stream()
				.sorted(Map.Entry
				.comparingByValue(
				(Comparator.comparing(IncreaseDetails::getRealIncrease).reversed())))
				.collect(Collectors
				.toMap(Map.Entry::getKey,Map.Entry::getValue,(a,b)-> a, LinkedHashMap::new));
		return sortedMap;
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
	private TreeMap<Integer,Year> parseYears(
			TreeMap<Integer,Year> years, Integer sY, Integer eY) throws Exception	
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

	/*
	 *  Calculates the Pearson's correlation. Probably Java already offered
	 *  a library for this purpose. Honestly, I reinvented the wheel as I 
	 *  have thought about the possibility of using a proper library way too late.
	 */
	
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

