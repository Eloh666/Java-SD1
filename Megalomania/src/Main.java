import java.io.*;

import javax.swing.*;

public class Main 
{
 static Country [] world = new Country[262];
 public static void main(String[] args) throws Exception
 {
	FileReader fr = new FileReader("bbc.txt");
	BufferedReader br = new BufferedReader(fr);
	for (int i=0;i<262;i++)
	{
	  String [] fld = br.readLine().split("\t");
	  world[i] = new Country(fld[0],fld[1],
					Integer.parseInt(fld[2]),
					Integer.parseInt(fld[3]),
					Long.parseLong(fld[4]));
	}
		
	String s = JOptionPane.showInputDialog("1 = World Population\n2 = Europe Population\n3 = Mexico Population\n4 = Highest Total POP\n5 = Countries of north America\n6 = Countries of SA\n7 = China pop as a multiple of UK\n8 = Bigger pop between Thailand and Taiwan\n9 =Countries in asia and asia pacific\n10 =Countries containing land");
	int n = Integer.parseInt(s);
	if(n == 1)
	{
		double worldPop = 0;
		for(Country i : world)
		{
			worldPop = worldPop + i.pop;
		}
		System.out.println("The population of the world is "+worldPop);
	}
	if(n == 2)
	{
		double euPop = 0;
		for(Country i : world)
		{
			if(i.region.equals("Europe"))
			euPop = euPop + i.pop;
		}
		System.out.println("The population of Europe is "+euPop);
	}
	if(n == 3)
	{
		for(Country i : world)
		{
			if(i.name.equals("Mexico"))
				System.out.println("The population of Mexico is "+i.pop);
		}
	}
    if(n == 4)
    {
    	double max = world[0].pop;
    	String maxCountry = world[0].name;
        for(Country i : world)
        {
            if(i.pop > max)
            {
            	max = i.pop;
            	maxCountry = i.name;
            }
        }
        
        System.out.println("World mos pulated country is "+maxCountry+" with a polulation of "+max);
    }
    if(n == 5)
    {
    	int naCountries = 0;
        for(Country i : world)
        {
            if(i.region.equals("North America"))
                naCountries += 1;
        }
        System.out.println("The number of North American countries is"+naCountries);
    }
    if(n == 6)
    {
    	System.out.println("South American countries :\n");
        for(Country i : world)
        {
            if(i.region.equals("South America"))
                System.out.println(i.name);
        }
    }
    if(n == 7)
    {
    	double cPop = 0;
    	double ukPop = 0;
        for(Country i : world)
        {
            if(i.name.equals("China"))
                cPop += i.pop;
            if(i.name.equals("United Kingdom"))
                ukPop += i.pop;
        }
        System.out.println("The population of the China is "+cPop/ukPop+" as high as the poulation of the UK");
    }
    if(n == 8)
    {
    	double max = 0;
    	String coNam = "";
        for(Country i : world)
        {
            if(i.name.equals("Thailand") || i.name.equals("Taiwan"))
            	if(i.pop > max)
            		max = i.pop;
            		coNam = i.name;
            		
        }
        System.out.println(coNam+"has the highest population, which is "+max);
    }
    if(n == 9)
    {
    	int ctrs = 0;
        for(Country i : world)
        {
            if(i.region.equals("Asia") || i.region.equals("Asia-Pacific"))
            	ctrs += 1;
        }
        System.out.println("The total amount of contries of Asia and Asia-Pacific combines is: "+ctrs);
    }
    if(n == 10)
    {
    	System.out.println("Countries that contain Land in their names: \n");
        for(Country i : world)
        {
            if(i.name.contains("land"))
            	if(!(i.name.endsWith("Islands") || i.name.endsWith("Islands")))
            		System.out.println(i.name);
        }
    }

    String s2 = JOptionPane.showInputDialog("1 = Identical Pop\n2 = Contained in the second \n3 = First three letters\n4 = Length and character in common");
    int n2 = Integer.parseInt(s);
    if(n == 1)
	{
		for(Country i : world)
		{
			int pop1 = i.pop / 1000000;
			if(pop1 > 13)
				for(Country j : world)
				{
					int pop2 = j.pop / 1000000;
					if(pop2 > 13)
							if (pop1 == pop2 && i.name != j.name)
								System.out.println(i.name+" "+pop1+" "+j.name+" "+pop2);
				}
		}
	}
    if(n == 2)
	{
		for(Country i : world)
		{
			for(Country j : world)
			{	
			    if(i.name.contains(j.name) && ! i.name.equals(j.name))
			    	System.out.println(i.name+"  "+j.name);
			}
		}
	}
    if(n == 3)
	{
    	System.out.println("Option 3");
		for(Country i : world)
		{
			for(Country j : world)
			{	
			    if(i.name.substring(0, 3).equals(j.name.substring(0, 3)))
			    	if(!i.name.equals(j.name))
			    		System.out.println(i.name+"  "+j.name);
			}
		}
	}

  }
}