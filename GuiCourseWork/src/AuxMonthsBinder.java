import java.util.ArrayList;

/*
 * Custom data structure for Question 12 and 13.
 * Each single "AuxMonthsBinder" (yes I am not great with names)
 * provides storage for a "calendar" month.
 * Each month includes
 * - A summative value for each day of that week.
 * - A value for the number of days in that months,
 *   dynamically calculated and and works with 29months Februaries.
 * - An average value for the daily data for that specific month
 * 
 * The methods to add days are also provided.
 */

public class AuxMonthsBinder {

	ArrayList<Float> values;
	private Integer daysCount = 0;
	
	public AuxMonthsBinder() {
		this.values = new ArrayList<Float>();
	}

	public ArrayList<Float> getValue() {
		return values;
	}
	public void setValue(ArrayList<Float> values) {
		this.values = values;
	}			
	
	public Integer getDays() {
		return daysCount;
	}
	
	public void setDays(Integer days) {
		this.daysCount = days;
	}
	
	/*
	 * calculates and returns the average usage
	 * for the 5 languages of the sample
	 */
	public Float[] getAverage()
	{
		
		Float acc[] = {0f,0f,0f,0f,0f};

		for(Integer i =0; i<=this.values.size()-5;i+=5)
		{
			acc[0] +=values.get(i);
			acc[1] +=values.get(i+1) ;
			acc[2] +=values.get(i+2) ;
			acc[3] +=values.get(i+3) ;
			acc[4] +=values.get(i+4) ;
		}
		
		for (Integer i =0; i< 5; i++)
		{
			if(this.daysCount != 0)
				acc[i] = acc[i]/this.daysCount;
		}
		return acc;
	}
	
	/*
	 * calculates and returns the
	 * full data for a specific language
	 */
	
	public Float getValueForLanguage(Integer language)
	{
		
		Float acc[] = {0f,0f,0f,0f,0f};
		for(Integer i =0; i<=this.values.size()-5;i+=5)
		{
			acc[0] +=values.get(i);
			acc[1] +=values.get(i+1) ;
			acc[2] +=values.get(i+2) ;
			acc[3] +=values.get(i+3) ;
			acc[4] +=values.get(i+4) ;
		}
		return acc[language];
	}
	
	public void addDays(Integer days)
	{
		this.daysCount += days;
	}

}
