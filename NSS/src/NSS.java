public class NSS {
	public String UKPRN; // A unique identifier for each institution
	public String institution; // The name of the institution
	public String subject; // The subject of the degree studied
	public String level; // You may ignore this field
	public int question; // Questions numbered 1-22
	public int[] answered; // The percentage of students who responded
									// 0 - STRONGLY_DISAGREE to
									// 4 - STRONGLY_AGREE
	public int response; // Number of students who responded
	public int sample; // The number of students eligible to respond
	public boolean aggregate;  // You may ignore this field
	
	//Responses are coded in the answered array
	//For example answered[NSS.AGREE] gives the number of students
	//Who responded AGREE to that question
	public static int STRONGLY_DISAGREE = 0;
	public static int DISAGREE = 1;
	public static int NEUTRAL = 2;
	public static int AGREE = 3;
	public static int STRONGLY_AGREE = 4;
	NSS(String line){
		String [] f = line.split("\t");
		this.UKPRN = f[0].trim();
		this.institution = f[1].trim();
		this.subject = f[2].trim();
		this.level = f[3].trim();
		try
		{
			this.question = Integer.parseInt(f[4].substring(1,3));
		}
		catch (Exception ex)
		{
			this.question = -1;
		}
		this.answered = new int[5];
		for(int i=0;i<5;i++){
			this.answered[i] = Integer.parseInt(f[5+i].substring(0,f[5+i].length()-1));
		}
		this.response = Integer.parseInt(f[14]);
		this.sample = Integer.parseInt(f[15]);
	}
}
