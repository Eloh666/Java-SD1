
public class CompTool implements java.util.Comparator<CountryStats> {
	
	public int compare(CountryStats first, CountryStats second)
	{
		Float one = first.getRealIncrease();
		Float two = second.getRealIncrease();
		return -1*one.compareTo(two);
	}
}
