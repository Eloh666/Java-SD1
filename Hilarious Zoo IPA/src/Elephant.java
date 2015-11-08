import java.util.Random;

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
		super.setMinAge(0);
		super.setMaxAge(50);
		super.setMinWeight(50);
		super.setMaxWeight(3000);
		super.setMinHeight(50);
		super.setMaxHeight(4000);
		super.setMinSpeed(0);
		super.setMaxSpeed(4);
		super.setPackLeaderIdentifier("Eldest Female");
	}
	
	Elephant()
	{
		this("Namless Elephant","Male",25,6500,4);
	}
}