import java.util.ArrayList;

public class Animal 
{
	private String type = "Animal";
	private String name;
	private String gender;
	private int age;
	private float weight;
	private float height;
	private ArrayList<String> preys;

	Animal(String inpName, String inpGender, int inpAge, float inpWeight, float inpHeight)
	{
		this.name = inpName;
		this.gender = inpGender;
		this.age = inpAge;
		this.weight = inpWeight;
		this.height = inpHeight;
		this.preys = new ArrayList<String>();
	}
	
	Animal()
	{
		this("Namless Animal","Male",13,10,0.2f);
	}
	
	String greet()
	{
		return "Hello";
	}

	public String toString()
	{
		return name+" is an "+type;
	}
	
	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String getGender() {
		return gender;
	}

	public int getAge() {
		return age;
	}

	public float getWeight() {
		return weight;
	}

	public float getHeight() {
		return height;
	}

	public ArrayList<String> getPreys() {
		return preys;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public void setPreys(ArrayList<String> preys) {
		this.preys = preys;
	}
	
	
	
	

}
