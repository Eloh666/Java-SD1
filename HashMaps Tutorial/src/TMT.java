import java.util.TreeMap;
import java.io.BufferedReader;
import java.io.FileReader;

public class TMT{

	public static void main(String [] argv)throws Exception{
		TreeMap<String,Country> world = new TreeMap<String,Country>();
		BufferedReader fh =
				new BufferedReader(new FileReader("bbc.txt"));
		String line;
		while ((line=fh.readLine())!=null){
			String [] wrds = line.split("\t");
			world.put(wrds[0],new Country(wrds[0], wrds[1],Integer.parseInt(wrds[2]),
					Long.parseLong(wrds[3]),Long.parseLong(wrds[4])));
		}
		IntroducingCountry(world);
		fh.close();
		
	}

	static void IntroducingCountry(TreeMap<String,Country> world){
		//GDP for Italy
		Country it = world.get("Italy");
		System.out.printf("1. GDP of Italy $%,d%n", it.gdp);

		//Print the combined area of Belgium, The Netherlands, Luxembourg
		Country nl = world.get("The Netherlands");
		Country lu = world.get("Luxembourg");
		Country bl = world.get("Belgium");
		System.out.println("2. Benelux Area: "+lu.area+nl.area+bl.area);


		//Print the population of China divided by the population of the United Kingdom.
		
		Country Cn = world.get("China");
		Country Uk = world.get("United Kingdom");
		System.out.println("3. The area of China is "+Cn.area/Uk.area+" the area of the United Kingdom");
	
		//Print the name, the population and the per capita GDP for France and for Germany.
		
		Country Fr = world.get("France");
		Country Gm = world.get("Germany");
		System.out.printf("The population of %s is: %d and it's per capita GDP is %d\n",Fr.name,Fr.pop,Fr.gdp/Fr.pop);
		System.out.printf("The population of %s is: %d and it's per capita GDP is %d\n",Gm.name,Gm.pop,Gm.gdp/Gm.pop);
		
		
		
	}
}
