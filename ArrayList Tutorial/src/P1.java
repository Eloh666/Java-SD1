import java.io.*;
public class P1 {

	public static void main(String[] args) throws Exception{
		BufferedReader br =
				new BufferedReader(new FileReader("bbc.txt"));
		Country[] world = new Country[262]; 
		for(int i=0;i<194;i++)
			world[i] = new Country(br.readLine());
		System.out.println(world[0].name);
		System.out.println(world[42]);
	}

}
