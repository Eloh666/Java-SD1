public class Duck 
{
	private String name;
	private String type = "Duck";
	private String cry = "''Quack Quack''";
	private String gender;
	private int age;
	private float weight;
	private float height;
	
	Duck(String inpName, String inpGender, int inpAge, float inpWeight, float inpHeight)
	{
		this.name = inpName;
		this.gender = inpGender;
		this.age = inpAge;
		this.weight = inpWeight;
		this.height = inpHeight;
	}
	
	Duck()
	{
		this("Namless Duck","Male",3,3,0.14f);
	}
	
	String greet()
	{
		return cry;
	}
	
	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getCry() {
		return cry;
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

	public void setName(String name) {
		this.name = name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setCry(String cry) {
		this.cry = cry;
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
}