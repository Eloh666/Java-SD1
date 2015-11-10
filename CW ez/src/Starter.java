import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Starter{
	public static void main(String[] args) throws Exception {
		BufferedReader fh =
				new BufferedReader(new FileReader("iot.txt"));
		//First line contains the language names
		String s = fh.readLine(); 
		List<String> langs =
				new ArrayList<>(Arrays.asList(s.split("\t")));
		langs.remove(0);	//Throw away the first word - "week"
		Map<String,HashMap<String,Integer>> iot = new TreeMap<>();
		while ((s=fh.readLine())!=null)
		{
			String [] wrds = s.split("\t");
			HashMap<String,Integer> interest = new HashMap<>();
			for(int i=0;i<langs.size();i++)
				interest.put(langs.get(i), Integer.parseInt(wrds[i+1]));
			iot.put(wrds[0], interest);
		}
		fh.close();
		HashMap<Integer,HashMap<String,HashMap<String,Integer>>>
			regionsByYear = new HashMap<>();
		for (int i=2004;i<2016;i++)
		{
			BufferedReader fh1 =
					new BufferedReader(new FileReader(i+".txt"));
			String s1 = fh1.readLine(); //Throw away the first line
			HashMap<String,HashMap<String,Integer>> year = new HashMap<>();
			while ((s1=fh1.readLine())!=null)
			{
				String [] wrds = s1.split("\t");
				HashMap<String,Integer>langMap = new HashMap<>();
				for(int j=1;j<wrds.length;j++){
					langMap.put(langs.get(j-1), Integer.parseInt(wrds[j]));
				}
				year.put(wrds[0],langMap);
			}
			regionsByYear.put(i,year);
			fh1.close();
		}
		// Data structures available are: langs, iot, regionsByYear
		// langs is a list of languages
		// iot is interest over time - a map from week to a map from languages
		// regionsByYear maps years onto a map of regions onto a map of languages

		Question51(regionsByYear);
	}
	
	
	public static void Question5(HashMap<Integer,HashMap<String,HashMap<String,Integer>>> regionsByYear)
	{
		ArrayList<String> nigsWithoutPython = new ArrayList<>();
		for(HashMap<String,HashMap<String,Integer>> year: regionsByYear.values())
		{
			for(String s: year.keySet())
			{
				if(year.get(s).get("python") == 0)
				{
					if(!nigsWithoutPython.contains(s))
						nigsWithoutPython.add(s);
				}
				if (year.get(s).get("python") != 0)
				{
					if(nigsWithoutPython.contains(s))
						nigsWithoutPython.remove(s);
				}
			}
		}
		for(HashMap<String,HashMap<String,Integer>> year: regionsByYear.values())
		{
			for(String s: year.keySet())
			{

				if(year.get(s).get("python") != 0)
				{
					if(nigsWithoutPython.contains(s))
						nigsWithoutPython.remove(s);
				}
			}
		}
		System.out.println(nigsWithoutPython.size());
	}
	
	
	
	
	public static void Question51(HashMap<Integer,HashMap<String,HashMap<String,Integer>>> regionsByYear)
	{
		HashMap<String, Boolean> nigsWithoutPython = new HashMap<String, Boolean>();
		for(HashMap<String,HashMap<String,Integer>> year: regionsByYear.values())
		{
			for(String s: year.keySet())
			{
				if(year.get(s).get("python") == 0)
				{
					if(!nigsWithoutPython.containsKey(s))
						nigsWithoutPython.put(s,false);
				}
				else
					if(nigsWithoutPython.containsKey(s))
						nigsWithoutPython.replace(s, true );
			}
		}

		int k = 0;
		for(String s : nigsWithoutPython.keySet())
			if(nigsWithoutPython.get(s) == false)
				k++;
		
		System.out.println(k+"    "+nigsWithoutPython.size());
	}

}
