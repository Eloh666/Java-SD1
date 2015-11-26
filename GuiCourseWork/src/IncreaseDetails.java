public class IncreaseDetails {
	
	/*
	 * Custom auxiliar structure for week 11.
	 * It simply provides a triple float array with
	 * a fixAverage method used to calculated the growth
	 * between the first two values.
	 */
	
	private Float firstValue;
	private Float secondValue;
	private Float realIncrease;
	
	public IncreaseDetails(Float firstValue, Float secondValue) {
		this.firstValue = firstValue;
		this.secondValue = secondValue;
		this.realIncrease = -1f;
	}	
	
	public Float getFirstValue() {
		return firstValue;
	}
	public void setFirstValue(Float firstValue) {
		this.firstValue = firstValue;
	}
	public Float getSecondValue() {
		return secondValue;
	}
	public void setSecondValue(Float secondValue) {
		this.secondValue = secondValue;
	}
	public Float getRealIncrease() {
		return realIncrease;
	}
	public void setRealIncrease(Float realIncrease) {
		this.realIncrease = realIncrease;
	}
	
	public void fixAverage()
	{
		if(this.firstValue != 0f)
		{
			this.realIncrease = (float) (((secondValue - firstValue)/firstValue))*100;
		}
			
	}

}
