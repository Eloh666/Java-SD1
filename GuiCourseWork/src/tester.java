
public class tester {

	public static void main(String[] args) {
		String test = "2004-01-04 - 2004-01-10	88	23	12	8	34";
		Week testerino = new Week(test);
		System.out.println(testerino.getStartDate());
		System.out.println(testerino.getStartDate().getMonthValue());
		System.out.println(testerino.getStartDate().getYear());
	}

}