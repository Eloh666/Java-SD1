import java.io.BufferedReader;
import java.io.FileReader;
import java.util.TreeMap;


public class Parser {
	public static void main(String[] args)  throws Exception {
		TreeMap<String,Institution> all = new TreeMap<String,Institution>();
		BufferedReader fh =
			new BufferedReader(new FileReader("NSS_2012_FT.txt"));
		String line;
		int questionAttempt = -1;
		while ((line=fh.readLine())!=null)
		{
			String lines[] = line.split("\t");
			lines[1] = lines[1].trim();
			lines[0] = lines[0].trim();
			
			if(!all.containsKey(lines[1]))
				{
					all.put(lines[1],new Institution(lines[0],lines[1]));
				}
			if(!all.get(lines[1]).getCohorts().containsKey(lines[2]))
			{
				Cohort temp = new Cohort(lines[2],Integer.parseInt(lines[15]),Integer.parseInt(lines[14]));
				all.get(lines[1]).addCohort(lines[2], temp);
				//System.out.println("IN  "+lines[1]+"   "+lines[2]);
			}
			int n[] = {
					Integer.parseInt(lines[5].substring(0,lines[5].length()-1)),
					Integer.parseInt(lines[6].substring(0,lines[6].length()-1)),
					Integer.parseInt(lines[7].substring(0,lines[7].length()-1)),
					Integer.parseInt(lines[8].substring(0,lines[8].length()-1)),
					Integer.parseInt(lines[9].substring(0,lines[9].length()-1))
			};
			try
			{
				questionAttempt = Integer.parseInt(lines[4].substring(1,3));
			}
			catch (Exception ex)
			{
				questionAttempt = -1;
			}
			Question temp2 = new Question(questionAttempt, n[3]+n[4],n);
			all.get(lines[1]).getCohorts().get(lines[2]).addQuestions(temp2);
		}
		fh.close();
		
		/*for (Institution k:all.values())
		{
			System.out.printf("\nInsitution : %s \n",k.getInstitution());
			for(Cohort j: k.getCohorts().values())
			{
				System.out.println("                       "+j.getSubject());
				for(Question z: j.getQuestions())
					System.out.printf("\nQuestion n %d values: %d\n",z.getQuid(),z.getAnswered()[4]);
			}
		}*/
	}

}