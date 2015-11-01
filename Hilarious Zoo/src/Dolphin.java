public class Dolphin 
{
	private String name;
	private String type = "Dolphin";
	private String cry = "''Eeeeeeeeeeee Eeeeeeeeeeeeeeeeee''";
	private String gender;
	private int age;
	private float weight;
	private float height;
	
	Dolphin(String inpName, String inpGender, int inpAge, float inpWeight, float inpHeight)
	{
		this.name = inpName;
		this.gender = inpGender;
		this.age = inpAge;
		this.weight = inpWeight;
		this.height = inpHeight;
	}
	
	Dolphin()
	{
		this("Namless Dolphin","Male",24,100,0.6f);
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