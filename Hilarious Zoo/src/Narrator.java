import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Narrator {

	public static void main(String[] args) 
	{
		JFrame frame = new JFrame();
		Object[] options = {"Yes, please",
                "No, let's random!","I'd rather learn more about..."};
		int questionDialog = JOptionPane.showOptionDialog(frame,
				"Do you want to chose the Animal names?",
						"Modality Selection",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options,
						options[1]);
		
		if(questionDialog == 0)
			userSelected(2);
		else if(questionDialog == 2)
			userSelected(1);
		else
			randomSelect();
	}
	
	static void userSelected(int amount)
	{
		JFrame frame = new JFrame();
		ArrayList<String> animals = new ArrayList<String>();
		for(int i = 0;i < amount; i++)
		{
			Object[] possibilities = {"Elephant", "Bear", "Cat","Dog","Dolphin","Duck","Monkey","Mouse","Snake","Tiger"};
			String s = (String)JOptionPane.showInputDialog(
			                    frame,
			                    "Select animal number "+(i+1),
			                    "Animal Selector",
			                    JOptionPane.PLAIN_MESSAGE,
			                    null,
			                    possibilities,
			                    "Elephant");
			animals.add(s);
		}
		if(amount == 2)
			makeObjects(animals);
		else
			randomMake(animals);
	}
	
	static void makeObjects(ArrayList<String> animals)
	{
		int available[] = {0,0,0,0,0,0,0,0,0,0};
		int behaviour [][];
		ArrayList<Object> animalsList = new ArrayList<Object>();
		ArrayList<String> animalNames = new ArrayList<String>();
		for(String i: animals)
		{
			Random generator = new Random(); 
			int name = generator.nextInt(16);
			animalNames.add(decodeName(name)); 
			switch (i) 
			{
	            case "Elephant":
	            	animalsList.add(new Elephant());
	            	available[0] = 1;
	                break;
	            case "Tiger":
	            	animalsList.add(new Tiger());
	            	available[1] = 1;
	                break;
	            case "Bear":
	        		animalsList.add(new Bear());
	        		available[2] = 1;
	                 break;
	            case "Monkey":
	        		animalsList.add(new Monkey());
	        		available[3] = 1;
	                 break;
	            case "Dog":
	        		animalsList.add(new Dog());
	        		available[4] = 1;
	                 break;
		        case "Cat":
		        	animalsList.add(new Cat());
		        	available[5] = 1;
		            break;
		        case "Duck":
		    		animalsList.add(new Duck());
		    		available[6] = 1;
		             break;
		        case "Snake":
		    		animalsList.add(new Snake());
		    		available[7] = 1;
		             break;
		        case "Dolphin":
		    		animalsList.add(new Dolphin());
		    		available[8] = 1;
		             break;
		        case "Mouse":
		    		animalsList.add(new Mouse());
		    		available[9] = 1;
		             break;
			}
		}
		behaviour = initBehaviour(available);
		narrator(animalsList, behaviour, animals, animalNames);
	}
	
	
	static int[][] initBehaviour(int a[])
	{                       // runs?             from who?
		int result[][] = {{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}};
// 									x scares y
//                                 e  t  b  m  d  c  d  s  d  m
		int [][] behaviour = {   { 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}, // e
								 { 1, 0, 0, 1, 1, 0, 1, 0, 0, 1}, // t
								 { 0, 0, 0, 0, 1, 1, 1, 0, 0, 1}, // b
								 { 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // m
								 { 0, 0, 0, 0, 0, 1, 1, 0, 0, 1}, // d
								 { 0, 0, 0, 0, 0, 0, 1, 0, 0, 1}, // c
								 { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // d
								 { 0, 0, 0, 0, 0, 0, 0, 0, 1, 1}, // s
								 { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, // d
								 { 1, 0, 0, 0, 0, 0, 0, 0, 0, 1}, // m
                             }; 
		for(int i = 0; i < 10; i++)
		{
			if(a[i] == 1)
			{
				boolean flag = false;
				for(int j = 0; j< 10 && !flag; j++)
				{
					if((behaviour [j][i] == 1 && a[j] == 1))               // O(n^2) max 100
					{
						flag = true;
						result[0][i] = 1;
						result[1][i] = j;
					}
				}
			}
		}
		return result;
	}
	
	
	static void narrator(ArrayList<Object> animalsList, int[][] behaviour, ArrayList<String> animals, ArrayList<String> animalNames)
	{
		String scared = "";
		String story = "One day in a hilarious wild zoo";
		int length = animals.size();
		for(int i = 0; i < length; i++)
		{
			if(i == length -1)
				story += " and";
			else
				story += ",";
			if(animals.get(i) == "Elephant")
			{
				story += " an "+animals.get(i);
				story += " named "+animalNames.get(i);
			}
			else
			{
				story += " a "+animals.get(i);
				story += " named "+animalNames.get(i);
			}
		}
		story += " meet ";
		Random generator = new Random(); 
		int location = generator.nextInt(4);
		story += decodeLocation(location)+"\n";
		
		for(int i = 0; i < length; i++)
		{			
	// ------------------ Elephant
			
			if(animalsList.get(i) instanceof Elephant)
			{
				if(behaviour[0][0] == 0)
				{
					story += animalNames.get(i)+" the "+"Elephant says"+" "+((Elephant) animalsList.get(i)).greet()+". ";
				}
				else
				{
					scared += animalNames.get(i)+" the "+"Elephant runs away scared by the "+deCode(behaviour[1][0])+". ";
				}
			}
			
	// ------------------ Tiger
			
			if(animalsList.get(i) instanceof Tiger)
			{
				if(behaviour[0][1] == 0)
				{
					story += animalNames.get(i)+" the "+"Tiger says"+" "+((Tiger) animalsList.get(i)).greet()+". ";
				}
				else
				{
					scared += animalNames.get(i)+" the "+"Tiger runs away scared by the "+deCode(behaviour[1][1])+". ";
				}
			}
			
	// ------------------ Bear
			
			if(animalsList.get(i) instanceof Bear)
			{
				if(behaviour[0][2] == 0)
				{

					story += animalNames.get(i)+" the "+"Bear says"+" "+((Bear) animalsList.get(i)).greet()+". ";
				}
				else
				{
					scared += animalNames.get(i)+" the "+"Bear runs away scared by the "+deCode(behaviour[1][2])+". ";
				}
			}
			
	// ------------------ Monkey
			
			if(animalsList.get(i) instanceof Monkey)
			{
				if(behaviour[0][3] == 0)
				{

					story += animalNames.get(i)+" the "+"Monkey says"+" "+((Monkey) animalsList.get(i)).greet()+". ";
				}
				else
				{
					scared += animalNames.get(i)+" the "+"Monkey runs away scared by the "+deCode(behaviour[1][3])+". ";
				}
			}
			
			
	// ------------------ Dog
			
			if(animalsList.get(i) instanceof Dog)
			{
				if(behaviour[0][4] == 0)
				{
					story += animalNames.get(i)+" the "+"Dog says"+" "+((Dog) animalsList.get(i)).greet()+". ";
				}
				else
				{
					scared += animalNames.get(i)+" the "+"Dog runs away scared by the "+deCode(behaviour[1][4])+". ";
				}
			}
			
	// ------------------ Cat
			
			if(animalsList.get(i) instanceof Cat)
			{
				if(behaviour[0][5] == 0)
				{
					story += animalNames.get(i)+" the "+"Cat says"+" "+((Cat) animalsList.get(i)).greet()+". ";
				}
				else
				{
					scared += animalNames.get(i)+" the "+"Cat runs away scared by the "+deCode(behaviour[1][5])+". ";
				}
			}
			
	// ------------------ Duck
			
			if(animalsList.get(i) instanceof Duck)
			{
				if(behaviour[0][6] == 0)
				{
					story += animalNames.get(i)+" the "+"Duck says"+" "+((Duck) animalsList.get(i)).greet()+". ";
				}
				else
				{
					scared += animalNames.get(i)+" the "+"Duck runs away scared by the "+deCode(behaviour[1][6])+". ";
				}
			}
			
	// ------------------ Snake
			
			if(animalsList.get(i) instanceof Snake)
			{
				if(behaviour[0][7] == 0)
				{
					story += animalNames.get(i)+" the "+"Snake says"+" "+((Snake) animalsList.get(i)).greet()+". ";
				}
				else
				{
					scared += animalNames.get(i)+" the "+"Snake runs away scared by the "+deCode(behaviour[1][7])+". ";
				}
			}
			
	// ------------------ Dolphin
			
			if(animalsList.get(i) instanceof Dolphin)
			{
				if(behaviour[0][8] == 0)
				{
					story += animalNames.get(i)+" the "+"Dolphin says"+" "+((Dolphin) animalsList.get(i)).greet()+". ";
				}
				else
				{
					scared += animalNames.get(i)+" the "+"Dolphin runs away scared by the "+deCode(behaviour[1][8])+". ";
				}
			}
			
	// ------------------ Mouse
			
			if(animalsList.get(i) instanceof Mouse)
			{
				if(behaviour[0][9] == 0)
				{

					story += animalNames.get(i)+" the "+"Mouse says"+" "+((Mouse) animalsList.get(i)).greet()+". ";
				}
				else
				{
					scared += animalNames.get(i)+" the "+"Mouse runs away scared by the "+deCode(behaviour[1][9])+". ";
				}
			}
		}
		story += scared;
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, story);
	}

	static String deCode(int n)
	{
		String decode = "";
		switch (n)
		{
		case 0:
			decode = "Elephant";
			break;
		case 1:
			decode = "Tiger";
			break;
		case 2:
			decode = "Bear";
			break;
		case 3:
			decode = "Monkey";
			break;
		case 4:
			decode = "Dog";
			break;
		case 5:
			decode = "Cat";
			break;
		case 6:
			decode = "Duck";
			break;
		case 7:
			decode = "Snake";
			break;
		case 8:
			decode = "Dolphin";
			break;
		case 9:
			decode = "Mouse";
			break;
		}
	return decode;
	}
	
	static void randomSelect()
	{
		ArrayList<String> animals = new ArrayList<String>();
		Random generator = new Random(); 
		int size = generator.nextInt(4)+2;
		for(int i = 0; i<size; i++)
		{
			animals.add(deCode(generator.nextInt(10-1)+1));	
		}
		makeObjects(animals);
	}
	
	static String decodeLocation(int i)
	{
		String location = "";
		switch (i)
		{
			case(0):
				location = "close to the pool.";
				break;
			case(1):
				location = "under a Huge Banana Tree.";
				break;
			case(2):
				location = "near the aquarium area.";
				break;
			case(3):
				location = "somewhere... I'm out of ideas.";
				break;
			case(4):
				location = "by a visitor's entrance.";
				break;		
		}
		return location;	
	}

	static String decodeName(int i)
	{
		String name = "";
		switch (i)
		{
			case(0):
				name = "Batman";
				break;
			case(1):
				name = "Thor";
				break;
			case(2):
				name = "Anomander Rake";
				break;
			case(3):
				name = "Kant";
			case(4):
				name = "Hegel";
				break;
			case(5):
				name = "Darh Vader";
				break;
			case(6):
				name = "Gandalf";
				break;
			case(7):
				name = "Ned Stark";
				break;
			case(8):
				name = "Oda Nobunaga";
				break;
			case(9):
				name = "Spongebob";
				break;
			case(10):
				name = "Kirkegard";
				break;
			case(11):
				name = "De Cartes";
				break;
			case(12):
				name = "Sauron";
				break;
			case(13):
				name = "Morgoth";
				break;
			case(14):
				name = "Django with a silent D.";
				break;
			case(15):
				name = "John Snow";
				break;
			case(16):
				name = "Paul Muad'Dib";
				break;	
		}
		return name;	
	}
	
	static void randomMake(ArrayList<String> animals)
	{
		ArrayList<String> usedAlready = new ArrayList<String>();
		Random generator = new Random();
		String gend = "";
		int age;
		float weight;
		float height;
		int size = generator.nextInt(15)+2;
		int name;
		ArrayList<Object> animalsList = new ArrayList<Object>();
		for(int count = 0; count < size; count++)
		{
			name = generator.nextInt(16);
			while((usedAlready.contains(decodeName(name))))
			{
				name = generator.nextInt(16);
				usedAlready.add(decodeName(name));				
			}
			int gender = generator.nextInt(2);
			if(gender == 0)
				gend = "Male";
			else
				gend = "Female";


			switch (animals.get(0)) 
			{
	            case "Elephant":
	            	age = generator.nextInt(60);
	            	weight = generator.nextInt(7000)+500;
	            	height = generator.nextInt(400)+100;
	            	Elephant ele = new Elephant(decodeName(name),gend,age,weight,height);
	            	animalsList.add(ele);
	                break;
	                
	            case "Tiger":
	            	age = generator.nextInt(20);
	            	weight = generator.nextInt(200)+50;
	            	height = generator.nextInt(100)+50;
	            	Tiger tig = new Tiger(decodeName(name),gend,age,weight,height);
	            	animalsList.add(tig);
	                break;

	            case "Bear":
	            	age = generator.nextInt(45);
	            	weight = generator.nextInt(1000)+50;
	            	height = generator.nextInt(300)+50;
	            	Bear bea = new Bear(decodeName(name),gend,age,weight,height);
	            	animalsList.add(bea);
	                break;


	            case "Monkey":
	            	age = generator.nextInt(45);
	            	weight = generator.nextInt(50)+2;
	            	height = generator.nextInt(100)+50;
	            	Monkey mon = new Monkey(decodeName(name),gend,age,weight,height);
	            	animalsList.add(mon);
	                break;
	                 
	            case "Dog":
	            	age = generator.nextInt(15);
	            	weight = generator.nextInt(50)+2;
	            	height = generator.nextInt(50)+20;
	            	Dog dog = new Dog(decodeName(name),gend,age,weight,height);
	            	animalsList.add(dog);
	                break;
	                 
		        case "Cat":
	            	age = generator.nextInt(20);
	            	weight = generator.nextInt(10)+2;
	            	height = generator.nextInt(35)+5;
	            	Cat cat = new Cat(decodeName(name),gend,age,weight,height);
	            	animalsList.add(cat);
	                break;
	                
		        case "Duck":
	            	age = generator.nextInt(3);
	            	weight = generator.nextInt(2)+0.5f;
	            	height = generator.nextInt(25)+5;
	            	Duck duc = new Duck(decodeName(name),gend,age,weight,height);
	            	animalsList.add(duc);
	                break;
		             
		        case "Snake":
	            	age = generator.nextInt(3);
	            	weight = generator.nextInt(100)+1f;
	            	height = generator.nextInt(15)+2;
	            	Snake sna = new Snake(decodeName(name),gend,age,weight,height);
	            	animalsList.add(sna);
	                break;
		             
		        case "Dolphin":
	            	age = generator.nextInt(60);
	            	weight = generator.nextInt(130)+1f;
	            	height = generator.nextInt(60)+2;
	            	Dolphin dol = new Dolphin(decodeName(name),gend,age,weight,height);
	            	animalsList.add(dol);
	                break;
		             
		        case "Mouse":
	            	age = generator.nextInt(1);
	            	weight = generator.nextInt(1)+0.2f;
	            	height = generator.nextInt(10)+0.5f;
	            	Mouse mou = new Mouse(decodeName(name),gend,age,weight,height);
	            	animalsList.add(mou);
	                break;       
			}
		}
		socialOrganization(animals.get(0),animalsList,size);
	}
	
	
	static void socialOrganization(String type, ArrayList<Object> animalsList, int size)
	{
		int male = 0;
		int female = 0;
		float young = 0;
		float old = 0;
		int eldestM = -1;
		int eldestF = -1;
		float biggestM = -1;
		float biggestF = -1;
		float avgWeight = 0;
		float avgHeight = 0;
		float avgAge = 0;
		float largestM = -1;
		float largestF = -1;
		int kingsAddress = -1;
		String regent = "";
		String socialInfo = "Currently there are "+size+" "+type+"s in the wild zoo. Amzong them,";
		
		switch(type)
		{
			case("Elephant"):                        // Elephant
				for(Object i:animalsList)
				{
					avgWeight += ((Elephant) i).getWeight();
					avgHeight += ((Elephant) i).getHeight();
					avgAge += ((Elephant) i).getAge();
					
					if(((Elephant) i).getGender().equals("Male"))
					{
						if(((Elephant) i).getWeight() > biggestM)
							biggestM = ((Elephant) i).getWeight();
						if(((Elephant) i).getHeight() > largestM)
							largestM = ((Elephant) i).getHeight();
						if(((Elephant) i).getAge() > eldestM)
							eldestM = ((Elephant) i).getAge();
						male++;
					}
					else
					{
						if(((Elephant) i).getWeight() > biggestF)
							biggestF = ((Elephant) i).getWeight();
						if(((Elephant) i).getHeight() > largestF)
							largestF = ((Elephant) i).getHeight();
						if(((Elephant) i).getAge() > eldestF)
						{
							eldestF = ((Elephant) i).getAge();
							kingsAddress = animalsList.indexOf(i);
						}
						female++;
					}
					if(((Elephant) i).getAge() < 20)
						young++;
					else
						old++;
				}
				regent = "The regent among them is the eldest female, her name is "+
				((Elephant) animalsList.get(kingsAddress)).getName()+". She is "+eldestF+" years old";
				break;
				
			case("Tiger"):                        // Tiger
				for(Object i:animalsList)
				{
					avgWeight += ((Tiger) i).getWeight();
					avgHeight += ((Tiger) i).getHeight();
					avgAge += ((Tiger) i).getAge();
					
					if(((Tiger) i).getGender().equals("Male"))
					{
						if(((Tiger) i).getWeight() > biggestM)
							biggestM = ((Tiger) i).getWeight();
						if(((Tiger) i).getHeight() > largestM)
						{
							largestM = ((Tiger) i).getHeight();
							kingsAddress = animalsList.indexOf(i);
						}
						if(((Tiger) i).getAge() > eldestM)
							eldestM = ((Tiger) i).getAge();
						male++;
					}
					else
					{
						if(((Tiger) i).getWeight() > biggestF)
							biggestF = ((Tiger) i).getWeight();
						if(((Tiger) i).getHeight() > largestF)
							largestF = ((Tiger) i).getHeight();
						if(((Tiger) i).getAge() > eldestF)
							eldestF = ((Tiger) i).getAge();
						female++;
					}
					if(((Tiger) i).getAge() < 5)
						young++;
					else
						old++;				
				}
				regent = "The regent among them is the largest male, his name is "
				+((Tiger) animalsList.get(kingsAddress)).getName()+". His size is "+largestM+"Kgs";
				break;
			
			case("Bear"):                        // Bear
				for(Object i:animalsList)
				{
					avgWeight += ((Bear) i).getWeight();
					avgHeight += ((Bear) i).getHeight();
					avgAge += ((Bear) i).getAge();
					
					if(((Bear) i).getGender().equals("Male"))
					{
						if(((Bear) i).getWeight() > biggestM)
							biggestM = ((Bear) i).getWeight();
						if(((Bear) i).getHeight() > largestM)
							largestM = ((Bear) i).getHeight();
						if(((Bear) i).getAge() > eldestM)
						{
							eldestM = ((Bear) i).getAge();
							kingsAddress = animalsList.indexOf(i);
						}
						male++;
					}
					else
					{
						if(((Bear) i).getWeight() > biggestF)
							biggestF = ((Bear) i).getWeight();
						if(((Bear) i).getHeight() > largestF)
							largestF = ((Bear) i).getHeight();
						if(((Bear) i).getAge() > eldestF)
							eldestF = ((Bear) i).getAge();
						female++;
					}
					if(((Bear) i).getAge() < 5)
						young++;
					else
						old++;				
				}
				regent = "The regent among them is the eldest male, his name is "
				+((Bear) animalsList.get(kingsAddress)).getName()+". He is "+eldestM+" years old";
				break;
				
			case("Dog"):                        // Dog
				for(Object i:animalsList)
				{
					avgWeight += ((Dog) i).getWeight();
					avgHeight += ((Dog) i).getHeight();
					avgAge += ((Dog) i).getAge();
					
					if(((Dog) i).getGender().equals("Male"))
					{
						if(((Dog) i).getWeight() > biggestM)
							biggestM = ((Dog) i).getWeight();
						if(((Dog) i).getHeight() > largestM)
							largestM = ((Dog) i).getHeight();
						if(((Dog) i).getAge() > eldestM)
						{
							eldestM = ((Dog) i).getAge();
							kingsAddress = animalsList.indexOf(i);
						}						
						male++;
					}
					else
					{
						if(((Dog) i).getWeight() > biggestF)
							biggestF = ((Dog) i).getWeight();
						if(((Dog) i).getHeight() > largestF)
							largestF = ((Dog) i).getHeight();
						if(((Dog) i).getAge() > eldestF)
							eldestF = ((Dog) i).getAge();
						female++;
					}
					if(((Dog) i).getAge() < 3)
						young++;
					else
						old++;				
				}
				regent = "The regent among them is the eldest male, his name is "
				+((Dog) animalsList.get(kingsAddress)).getName()+". He is "+eldestM+" years old";
				break;
				
			case("Cat"):                        // Cat
				for(Object i:animalsList)
				{
					avgWeight += ((Cat) i).getWeight();
					avgHeight += ((Cat) i).getHeight();
					avgAge += ((Cat) i).getAge();
					
					if(((Cat) i).getGender().equals("Male"))
					{
						if(((Cat) i).getWeight() > biggestM)
							biggestM = ((Cat) i).getWeight();
						if(((Cat) i).getHeight() > largestM)
							largestM = ((Cat) i).getHeight();
						if(((Cat) i).getAge() > eldestM)
							eldestM = ((Cat) i).getAge();
						male++;
					}
					else
					{
						if(((Cat) i).getWeight() > biggestF)
							biggestF = ((Cat) i).getWeight();
						if(((Cat) i).getHeight() > largestF)
						{
							largestF = ((Cat) i).getHeight();
							kingsAddress = animalsList.indexOf(i);
						}
						if(((Cat) i).getAge() > eldestF)
							eldestF = ((Cat) i).getAge();
						female++;
					}
					if(((Cat) i).getAge() < 3)
						young++;
					else
						old++;				
				}
			regent = "The regent among them is the largest female, her name is "
				+((Cat) animalsList.get(kingsAddress)).getName()+". Her size is "+largestF+"Cms";
				break;
			
			case("Duck"):                        // Duck
				for(Object i:animalsList)
				{
					avgWeight += ((Duck) i).getWeight();
					avgHeight += ((Duck) i).getHeight();
					avgAge += ((Duck) i).getAge();
					
					if(((Duck) i).getGender().equals("Male"))
					{
						if(((Duck) i).getWeight() > biggestM)
							biggestM = ((Duck) i).getWeight();
						if(((Duck) i).getHeight() > largestM)
							largestM = ((Duck) i).getHeight();
						if(((Duck) i).getAge() > eldestM)
							eldestM = ((Duck) i).getAge();
						male++;
					}
					else
					{
						if(((Duck) i).getWeight() > biggestF)
						{
							biggestF = ((Duck) i).getWeight();
							kingsAddress = animalsList.indexOf(i);
						}
						if(((Duck) i).getHeight() > largestF)
							largestF = ((Duck) i).getHeight();
						if(((Duck) i).getAge() > eldestF)
							eldestF = ((Duck) i).getAge();
						female++;
					}
					if(((Duck) i).getAge() < 1)
						young++;
					else
						old++;				
				}
				regent = "The regent among them is the biggest female, her name is "
				+((Duck) animalsList.get(kingsAddress)).getName()+". Her size is "+biggestF+"Kgs";
				break;
		
			case("Monkey"):                        // Monkey
				for(Object i:animalsList)
				{
					avgWeight += ((Monkey) i).getWeight();
					avgHeight += ((Monkey) i).getHeight();
					avgAge += ((Monkey) i).getAge();
					
					if(((Monkey) i).getGender().equals("Male"))
					{
						if(((Monkey) i).getWeight() > biggestM)
							biggestM = ((Monkey) i).getWeight();
						if(((Monkey) i).getHeight() > largestM)
						{
							largestM = ((Monkey) i).getHeight();
							kingsAddress = animalsList.indexOf(i);
						}
						if(((Monkey) i).getAge() > eldestM)
							eldestM = ((Monkey) i).getAge();
						male++;
					}
					else
					{
						if(((Monkey) i).getWeight() > biggestF)
							biggestF = ((Monkey) i).getWeight();
						if(((Monkey) i).getHeight() > largestF)
							largestF = ((Monkey) i).getHeight();
						if(((Monkey) i).getAge() > eldestF)
							eldestF = ((Monkey) i).getAge();
						female++;
					}
					if(((Monkey) i).getAge() < 4)
						young++;
					else
						old++;				
				}
				regent = "The regent among them is the largest male, his name is "
				+((Monkey) animalsList.get(kingsAddress)).getName()+". His size is "+largestM+"Kgs";
				break;
		
			case("Mouse"):                        // Mouse
				for(Object i:animalsList)
				{
					avgWeight += ((Mouse) i).getWeight();
					avgHeight += ((Mouse) i).getHeight();
					avgAge += ((Mouse) i).getAge();
					
					if(((Mouse) i).getGender().equals("Male"))
					{
						if(((Mouse) i).getWeight() > biggestM)
							biggestM = ((Mouse) i).getWeight();
						if(((Mouse) i).getHeight() > largestM)
							largestM = ((Mouse) i).getHeight();
						if(((Mouse) i).getAge() > eldestM)
							eldestM = ((Mouse) i).getAge();
						male++;
					}
					else
					{
						if(((Mouse) i).getWeight() > biggestF)
							biggestF = ((Mouse) i).getWeight();
						if(((Mouse) i).getHeight() > largestF)
							largestF = ((Mouse) i).getHeight();
						if(((Mouse) i).getAge() > eldestF)
							eldestF = ((Mouse) i).getAge();
						female++;
					}
					if(((Mouse) i).getAge() < 1)
						young++;
					else
						old++;				
				}
				regent = "There are no regents among them... they are a democracy.";
				break;
				
			case("Snake"):                        // Snake
				for(Object i:animalsList)
				{
					avgWeight += ((Snake) i).getWeight();
					avgHeight += ((Snake) i).getHeight();
					avgAge += ((Snake) i).getAge();
					
					if(((Snake) i).getGender().equals("Male"))
					{
						if(((Snake) i).getWeight() > biggestM)
							biggestM = ((Snake) i).getWeight();
						if(((Snake) i).getHeight() > largestM)
							largestM = ((Snake) i).getHeight();
						if(((Snake) i).getAge() > eldestM)
							eldestM = ((Snake) i).getAge();
						male++;
					}
					else
					{
						if(((Snake) i).getWeight() > biggestF)
						{
							biggestF = ((Snake) i).getWeight();
							kingsAddress = animalsList.indexOf(i);
						}
						if(((Snake) i).getHeight() > largestF)
							largestF = ((Snake) i).getHeight();
						if(((Snake) i).getAge() > eldestF)
							eldestF = ((Snake) i).getAge();
						female++;
					}
					if(((Snake) i).getAge() < 6)
						young++;
					else
						old++;				
				}
				regent = "The regent among them is the biggest female, her name is "
				+((Snake) animalsList.get(kingsAddress)).getName()+". Her weight is "+biggestF+"Kgs";
				break;
				
			case("Dolphin"):                        // Dolphin
				for(Object i:animalsList)
				{
					avgWeight += ((Dolphin) i).getWeight();
					avgHeight += ((Dolphin) i).getHeight();
					avgAge += ((Dolphin) i).getAge();
					
					if(((Dolphin) i).getGender().equals("Male"))
					{
						if(((Dolphin) i).getWeight() > biggestM)
						{
							biggestM = ((Dolphin) i).getWeight();
							kingsAddress = animalsList.indexOf(i);
						}
						if(((Dolphin) i).getHeight() > largestM)
							largestM = ((Dolphin) i).getHeight();
						if(((Dolphin) i).getAge() > eldestM)
							eldestM = ((Dolphin) i).getAge();
						male++;
					}
					else
					{
						if(((Dolphin) i).getWeight() > biggestF)
							biggestF = ((Dolphin) i).getWeight();
						if(((Dolphin) i).getHeight() > largestF)
							largestF = ((Dolphin) i).getHeight();
						if(((Dolphin) i).getAge() > eldestF)
							eldestF = ((Dolphin) i).getAge();
						female++;
					}
					if(((Dolphin) i).getAge() < 6)
						young++;
					else
						old++;				
				}
				regent = "The regent among them is the biggest male, his name is "
				+((Dolphin) animalsList.get(kingsAddress)).getName()+". His weight is "+biggestF+"Kgs";
				break;	
			}
		socialInfo += " "+male+" are male and "+female+" are female.\n";
		socialInfo += "The percentage of young "+type+"s is "+(young/size)*100+"% while the adults are the remaining "+(old/size)*100+"%.\n";
		socialInfo += "The average age, weight and size of the pack are "+avgAge/size+" years -- "+avgWeight/size+"Kgs -- "+avgHeight/size+"Cms.\n";
		if(kingsAddress == -1)
			socialInfo += "Their regent recently died, they new one is still being elected";
		else
			socialInfo += regent;
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, socialInfo);
	}	
}

