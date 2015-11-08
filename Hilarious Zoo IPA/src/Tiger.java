public class Tiger extends Animal
{
	
	@Override
	String greet()
	{
		return "Roar Roar";
	}
	
	Tiger(String inpName, String inpGender, int inpAge, float inpWeight, float inpHeight)
	{
		super.setType("Tiger");
		super.getPreys().add("Elephant");
		super.getPreys().add("Dog");
		super.getPreys().add("Cat");
		super.getPreys().add("Monkey");
		super.getPreys().add("Mouse");
		
		
		super.setName(inpName);
		super.setGender(inpGender);
		super.setAge(inpAge);
		super.setWeight(inpWeight);
		super.setHeight(inpHeight);
		super.setMaxAge(50);
		super.setMinWeight(25);
		super.setMaxWeight(600);
		super.setMinHeight(20);
		super.setMaxHeight(120);
		super.setMinSpeed(2);
		super.setMaxSpeed(11);
		super.setPackLeaderIdentifier("Largest Male");
	}
	
	Tiger()
	{
		this("Namless Tiger","Male",25,200,0.60f);
	}
	

}