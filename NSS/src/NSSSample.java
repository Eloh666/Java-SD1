import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
 // Sample code on how to use the NSS class. Andrew Cumming
public class NSSSample{
	public static void main(String[] args)  throws Exception{
		ArrayList<NSS> all = new ArrayList<NSS>();
		BufferedReader fh =
			new BufferedReader(new FileReader("NSS_2012_FT.txt"));
		String line;
		while ((line=fh.readLine())!=null)
		{
			all.add(new NSS(line));
		}
		fh.close();
		
		q7(all);
	}
	
	static void q1(ArrayList<NSS> all)
	{
		for(NSS n: all)
		{
			if(n.subject.equals("(8) Computer Science")
				&& (n.question == 1)
				&& (n.UKPRN.equals("10007772")))
				System.out.println(n.institution);
		}
	}
	
	static void q2(ArrayList<NSS> all)
	{
		for(NSS n: all)
		{
			if(n.subject.equals("(8) Computer Science")
				&& (n.question == 1)
				&& (n.sample >= 300))
				System.out.println(n.institution);
		}
	}
	
	static void q3(ArrayList<NSS> all)
	{
		for(NSS n: all)
		{
		if(n.subject.equals("(8) Computer Science")
			&& (n.question == 1)
			&& (n.answered[4] > 50))
			System.out.println(n.institution);
		}
	}
	
	static void q4(ArrayList<NSS> all)
	{
		for(NSS n: all)
		{
			if(n.subject.equals("(8) Computer Science")
				&& (n.question == 15)
				&& ((n.answered[4] + n.answered[3])<50))
				System.out.println(n.institution);
		}
	}
	
	static void q5(ArrayList<NSS> all)
	{
		int compsci = 0;
		int design = 0;
		for(NSS n: all)
		{
			if(n.question == 22)
			{
				if(n.subject.equals("(8) Computer Science"))
					compsci++;
				if(n.subject.equals("(H) Creative Arts and Design"))
					design++;
			}
		}
		System.out.printf("Computer Science %d VS %d Creative Design\n",compsci,design);
	}
	
	static void q6(ArrayList<NSS> all)
	{
		for(NSS n: all)
		{
			if(((n.answered[4] + n.answered[3]) == 100)
				&& (n.question == 13))
				System.out.println(n.institution+"   "+n.subject);
		}
	}
	
	static void q7(ArrayList<NSS> all)
	{
		ArrayList<NSS> list = new ArrayList<>();
		ArrayList<String> instList = new ArrayList<>();
		for(NSS n: all)
		{
			if(n.subject.equals("(8) Computer Science"))
				if(!instList.contains(n.institution))
					{
						list.add(n);
						instList.add(n.institution);
					}
		}
		Comp compareSamples = new Comp();
		java.util.Collections.sort(list,compareSamples);
		for(int i = 0;i < 5;i++)
		{
			System.out.println(list.get(i).sample+"   "+list.get(i).institution);
		}
	}
}


