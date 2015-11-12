import java.util.ArrayList;

public class AuxMonthsBinder {

	ArrayList<Integer> values;
	private Float weeksCount = 0f;
	
	public AuxMonthsBinder() {
		this.values = new ArrayList<Integer>();
	}

	public ArrayList<Integer> getValue() {
		return values;
	}
	public void setValue(ArrayList<Integer> values) {
		this.values = values;
	}			
	
	public Float getWeeks() {
		return weeksCount;
	}
	
	public void setWeeks(Float weeks) {
		this.weeksCount = weeks;
	}
	
	public Float[] getAverage()
	{
		
		Float acc[] = {0f,0f,0f,0f,0f};

		for(Integer i =0; i<this.values.size()-5;i+=5)
		{
			acc[0] +=values.get(i);
			acc[0] +=values.get(i+1) ;
			acc[0] +=values.get(i+1) ;
			acc[0] +=values.get(i+2) ;
			acc[0] +=values.get(i+3) ;
		}
		
		for (Float f:acc)
		{
			f = f/this.weeksCount;
		}
		return acc;
	}
	
	public String toString()
	{
		return getAverage().toString();
	}
	
	public void addWeeks(float weeks)
	{
		this.weeksCount += weeks;
	}

}