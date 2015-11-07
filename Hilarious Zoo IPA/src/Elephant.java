public class Elephant extends Animal
{
	@Override
	String greet()
	{
		return "Baraaag Baraaag";
	}
	
	Elephant(String inpName, String inpGender, int inpAge, float inpWeight, float inpHeight)
	{
		super.setType("Elephant");
		
		super.setName(inpName);
		super.setGender(inpGender);
		super.setAge(inpAge);
		super.setWeight(inpWeight);
		super.setHeight(inpHeight);
	}
	
	Elephant()
	{
		this("Namless Elephant","Male",25,6500,4);
	}
}