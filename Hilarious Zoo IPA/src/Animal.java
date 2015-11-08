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
	private int minAge = 0;
	private int maxAge = 0;
	private int minWeight = 0;
	private int maxWeight = 0;
	private int minHeight = 0;
	private int maxHeight = 0;
	private int minSpeed = 0;
	private int maxSpeed = 0;
	private float moveSpeed = 0;
	private String packLeaderIdentifier = "None";

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

	public int getMinAge() {
		return minAge;
	}

	public void setMinAge(int minAge) {
		this.minAge = minAge;
	}

	public int getMaxAge() {
		return maxAge;
	}

	public void setMaxAge(int maxAge) {
		this.maxAge = maxAge;
	}

	public float getMinWeight() {
		return minWeight;
	}

	public void setMinWeight(int minWeight) {
		this.minWeight = minWeight;
	}

	public int getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(int maxWeight) {
		this.maxWeight = maxWeight;
	}

	public int getMinHeight() {
		return minHeight;
	}

	public void setMinHeight(int minHeight) {
		this.minHeight = minHeight;
	}

	public int getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}

	public int getMinSpeed() {
		return minSpeed;
	}

	public int getMaxSpeed() {
		return maxSpeed;
	}

	public float run() {
		return moveSpeed;
	}

	public void setMinSpeed(int minSpeed) {
		this.minSpeed = minSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	public void setMoveSpeed(float moveSpeed) {
		this.moveSpeed = moveSpeed;
	}
	
	public void setPackLeaderIdentifier(String packLeaderIdentifier) {
		this.packLeaderIdentifier = packLeaderIdentifier;
	}

	public String getPackLeaderIdentifier() {
		return packLeaderIdentifier;
	}
	
	
	
	

}
