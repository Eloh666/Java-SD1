import java.io.*;
import java.util.*;
public class BBC_ArrayList {

	public static void main(String[] args)throws Exception {
		BufferedReader br =
				new BufferedReader(new FileReader("bbc.txt"));
		ArrayList<Country> world = new ArrayList<Country>();
		for(String s=br.readLine();s!=null;s=br.readLine())
			world.add(new Country(s));
		System.out.println(world.size());
		System.out.println("Country 22: "+world.get(22));
	}
}
