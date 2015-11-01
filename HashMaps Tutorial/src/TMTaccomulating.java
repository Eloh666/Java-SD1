import java.io.BufferedReader;
import java.io.FileReader;
import java.util.TreeMap;


public class TMTaccomulating {

	public static void main(String [] argv)throws Exception
	{
		TreeMap<String,Country> world = new TreeMap<String,Country>();
		BufferedReader fh =
				new BufferedReader(new FileReader("bbc.txt"));
		String line;
		while ((line=fh.readLine())!=null)
		{
			String [] wrds = line.split("\t");
			world.put(wrds[0],new Country(wrds[0], wrds[1],Integer.parseInt(wrds[2]),
					Long.parseLong(wrds[3]),Long.parseLong(wrds[4])));
		}
		fh.close();
		Accumulating(world);

	}
	
	static void Accumulating(TreeMap<String,Country> world){
		//Use an accumulating parameter to calculate the total area of old Europe.
		String [] oldEU = {"Austria","Belgium" ,"Denmark" ,"Finland"
				,"France" ,"Germany" ,"Greece" ,"Ireland"
				,"Italy" ,"Luxembourg" ,"The Netherlands" ,"Portugal"
				,"Spain" ,"Sweden" ,"United Kingdom"};
		int called = 2;
		double acc = 0;
		double oldAcc = 0;
		double newAcc = 0;
		if(called == 0)
		{

			for (String s: oldEU)
			{
				acc += world.get(s).area;
				oldAcc += world.get(s).area;
			}
			System.out.println("The area of oldEU is : "+acc+" sq Kms");;
		}

		//Calculate the total area of Europe including new and old.
		String [] newEU = {"Cyprus", "Czech Republic", "Estonia",
				"Hungary", "Latvia", "Lithuania", "Malta",
				"Poland", "Slovakia", "Slovenia"};
		
		if(called == 0)
		{
			for (String s: newEU)
			{
				acc += world.get(s).area;
				newAcc += world.get(s).area;
			}
			System.out.println("The area of oldEU && newEU is : "+acc+" sq Kms");;
			System.out.println("The area of the old portion of europe is "+(oldAcc/acc)*100+"% of the total.");
			System.out.println("The area of the new portion of europe is "+(newAcc/acc)*100+"% of the total.");
		}

		//Calculate the percentage of Europe that is old or new. Do this for area, population and GDP.

		//The G8 summit in 2005 included:
		//Canada, France, Germany, Italy, Japan, Russia, United Kingdom and USA.
		//Calculate the total GDP for these countries and the total GDP for the planet.
		//Print the GDP of the G8 as a percentage of World GDP.
		
		if(called == 1)
		{
			String[] g8 = "Canada,France,Germany,Italy,Japan,Russia,United Kingdom,USA".split(",");
			double planetGDP = 0;
			double g8countriesGDP = 0;
			for (Country s: world.values())
			{
				planetGDP += s.gdp;
			}
			for (String s: g8)
			{
				g8countriesGDP += world.get(s).gdp;
			}
			System.out.println("The area of oldEU && newEU is : "+acc+" sq Kms");
			System.out.println("The total GDP of the G8 contries is "+(g8countriesGDP/planetGDP)*100+"% of the total");
		}
		
		//Print the number of countries smaller and the number of countries bigger than Mauritania.
		//Do not count countries with exactly the same population.
		
		if(called == 2)
		{
			int bigger = 0;
			int smaller = 0;
			for (Country c:world.values())
			{
				if(!(c.pop == world.get("Mauritania").pop))
				{
					if(c.area < world.get("Mauritania").area)
					{
						smaller++;
					}
					else if (c.area > world.get("Mauritania").area)
					{
						bigger++;
					}
				}
			}
			System.out.println("The number of countries smaller than Mauritania is "+smaller+".");
			System.out.println("The number of countries bigger than Mauritania is "+bigger+".");
		}
	}
}