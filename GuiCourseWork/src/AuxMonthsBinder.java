import java.util.ArrayList;

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
	
	public Integer getWeeks() {
		return daysCount;
	}
	
	public void setDays(Integer days) {
		this.daysCount = days;
	}
	
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
	
	public String toString()
	{
		return getAverage().toString();
	}
	
	public void addDays(Integer days)
	{
		this.daysCount += days;
	}

}