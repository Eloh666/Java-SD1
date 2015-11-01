import java.io.BufferedReader;
import java.io.FileReader;
import java.util.TreeMap;


public class TMTiteration {

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
		Iteration(world);

	}		
	
		static void Iteration(TreeMap<String,Country> world)
		{
			String [] opec = {"Algeria","Indonesia" ,"Iran" ,"Iraq",
					"Kuwait" ,"Libya" ,"Nigeria" ,"Qatar",
					"Saudi Arabia" ,"United Arab Emirates",	"Venezuela"};
			int called = 6;
			//Print the name and area of every country in South America.
			if(called == 1)
				for (Country c:world.values())
				{
					if(c.region.equals("South America"))
						System.out.printf("The area of %s is: %d\n",c.name,c.area);
				}
			
			//Print the name of each country with a population of at least 200 million.
			
			if(called == 2)
				for (Country c:world.values())
				{
					if(c.pop > 200000000)
						System.out.printf("%s has a population of over 200 million\n",c.name);
				}

			//Print the name of each country where the name begins with "J"
			
			if(called == 3)
				for (Country c:world.values())
				{
					if(c.name.startsWith("J"))
						System.out.printf("%s starts with J\n",c.name);
				}

			//Print the name of each country where GDP is greater than that of "United Kingdom"
			if(called == 4)
			{
				Country Uk = world.get("United Kingdom");
				for (Country c:world.values())
				{
					if(c.gdp > Uk.gdp)
						System.out.printf("%s has a greater GDP than the United Kingdom J\n",c.name);
				}
			}
			//For each of the countries of OPEC, print the name and the GDP.
			if(called == 5)
			{
				for (String s: opec)
				{
					System.out.println(world.get(s).name+"     "+world.get(s).gdp);
				}
			}
			//only for those OPEC members in the Middle East.
			if(called == 5)
			{
				for (String s: opec)
				{
					if(world.get(s).region.equals("Middle East"))
						System.out.println(world.get(s).name+"     "+world.get(s).gdp);
				}
			}
		}
}
