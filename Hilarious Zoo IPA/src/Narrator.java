import java.util.ArrayList;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Narrator {

	public static void main(String[] args) {                       // start the program
			ArrayList<String> animals = new ArrayList<>(); 		   // the list of animals that will be used by the animalsSelection method
			int mode = -1;
			mode = selectMode();                                   // let the user chose the mode of execution

			if(mode != -1)
			{
				if(mode == 1 || mode == 2)
					animals = userSelect(mode);								   // the user picks two animals
				else if(mode == 0)
					randomSelect();
				if(animals.get(0) != null)
				{
					if(mode == 0 || mode == 2)
						tellTale(makeObjects(animals,mode));
					else
						tellSocial(makeObjects(animals,mode));
				}
				else
				{
					printDialog("No animals selected, please try again."); // the user closed the window instead
				}
			}
			else
			{
				printDialog("No option selected, please try again."); // the user closed the window instead
			}
			
				
			
		}
	
	public static int selectMode()                                    // let's the user choose whether he wants to pick two animals,
	{																  // random them or simply find out the social hierarchy
		JFrame frame = new JFrame();
		Object[] options = {"Sure, let's random a story!","Later, first I wanna know more about...",
                "Ok, but I'd rather pick the Animals myself.."};
		int questionDialog = JOptionPane.showOptionDialog(frame,
				"Shall we tell a story?",
						"Modality Selection",
						JOptionPane.YES_NO_CANCEL_OPTION,
						JOptionPane.QUESTION_MESSAGE,
						null,
						options,
						options[1]);
		return questionDialog;
	}
	
	static ArrayList<String> userSelect(int amount)
	{
		JFrame frame = new JFrame();
		ArrayList<String> animals = new ArrayList<String>();
																// lets the user pick two animals from the menu
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
		// TODO add a check for the user not picking anything
		return animals;
	}
	
	static ArrayList<String> randomSelect()
	{
		String[] possibilities = {"Elephant", "Bear", "Cat","Dog","Dolphin","Duck","Monkey","Mouse","Snake","Tiger"};
		ArrayList<String> animals = new ArrayList<String>();
		Random generator = new Random(); 
		int size = generator.nextInt(4)+2;
		for(int i = 0; i<size; i++)
		{
			animals.add(possibilities[generator.nextInt(10-1)+1]);	
		}
		return animals;
	}
	
	public static void printDialog(String message)                   // simply reusable method that displays the given string as a dialog
	{
		JFrame frame = new JFrame();
		JOptionPane.showMessageDialog(frame, message);
	}
	
	public static ArrayList<Animal> makeObjects(ArrayList<String> animals, int mode)
	{	
		ElementsLibrary lib= new ElementsLibrary();		
		Random gen = new Random();
		int generated = -1;
		String genders[] = {"Male", "Female"}; 
		ArrayList<Animal> animalsList = new ArrayList<>();	  // the list of animal classes that will be used by the Narrator/social methods	
		if(mode == 1)
		{
			for(int i = 0; i < gen.nextInt(100)+5;i++)
			{
				animals.add(animals.get(0));
			}
		}
		for(String animal : animals)
		{
			Animal newAnimal = createObject(animal);
			generated = gen.nextInt(lib.getNames().size());      // set unique name
			newAnimal.setName(lib.getNames().get(generated));    
			lib.getNames().remove(generated);                           // Set specific information for each animal
			newAnimal.setGender(genders[gen.nextInt(2)]);       
			newAnimal.setAge(gen.nextInt(newAnimal.getMaxAge())+newAnimal.getMinAge());
			newAnimal.setHeight(gen.nextFloat()+gen.nextInt(newAnimal.getMaxHeight())+newAnimal.getMinHeight());
			newAnimal.setWeight(gen.nextFloat()+gen.nextInt(newAnimal.getMaxWeight())+newAnimal.getMinWeight());
			newAnimal.setMoveSpeed(gen.nextFloat()+gen.nextInt(newAnimal.getMaxSpeed())+newAnimal.getMinSpeed());
			animalsList.add(newAnimal);
		}
		
		return animalsList;
	}
	
	static Animal createObject(String className) {
	      Object object = null;
	      try 
	      {
	          Class<?> classDefinition = Class.forName(className);
	          object = classDefinition.newInstance();
	      } catch (InstantiationException e) 
	      {
	          object = new Animal();
	      } 
	      catch (IllegalAccessException e) 
	      {
	    	  object = new Animal();
	      } 
	      catch (ClassNotFoundException e) 
	      {
	          object = new Animal();
	      }
	      catch (NullPointerException e) 
	      {
	          object = new Animal();
	      }
	      return (Animal) object;                 // TODO REALLY not sure if I should do this
	   }
	
	static void tellTale(ArrayList<Animal> animalsList)
	{
		String story = "";
	}
	
	static void tellSocial(ArrayList<Animal> anList)
	{
		// if the "ruling" gender is unavailable might as well go Monty Python!
		String montyQuote = "well none really... they are an anarcho-syndicalist commune. They take it in turns to be a sort of executive officer for the week..."
				+ "\nbut all the decisions of that officer have to be ratified at a special bi-weekly meeting..."
				+ " \nby a simple majority in the case of purely internal affairs... etc";
		
		String social= "Currently there are "+anList.size()+" "+anList.get(0).getType()+"s in the zoo.";
		int males     = 0;
		int females   = 0;
		int youngsters = 0;
		int adults = 0;
		Animal eldestMale = new Animal();       // not ideal, but Animal class has only 0 values
		Animal eldestFemale = new Animal();
		Animal biggestMale = new Animal();
		Animal biggestFemale = new Animal();
		Animal largestMale = new Animal();
		Animal largestFemale = new Animal();
		
		for(Animal specimen: anList)
		{
			if((float) specimen.getAge()/specimen.getMaxAge() < 0.3)
				youngsters++;
			else
				adults++;
							
			if(specimen.getGender().equals("Male"))
			{
				if(specimen.getAge() > eldestMale.getAge())
					eldestMale = specimen;
				if(specimen.getWeight() > biggestMale.getWeight())
					eldestMale = specimen;
				if(specimen.getHeight() > largestMale.getHeight() )
					eldestMale = specimen;
				males++;
			}
			else
			{
				if(specimen.getAge() > eldestFemale.getAge())
					eldestFemale = specimen;
				if(specimen.getWeight() > biggestFemale.getWeight())
					eldestFemale = specimen;
				if(specimen.getHeight() > largestFemale.getHeight())
					eldestFemale = specimen;
				females++;
			}	
		}
		social += "\nOut of them "+(float) youngsters/anList.size() * 100+"% are young and the remaining "+(float) adults/anList.size() * 100+"% are adults.";
		social += "\nThe percentage of males is "+(float) males/anList.size() * 100+"% while the females are "+(float) females/anList.size() * 100+"% of the group.";
		social += "\nIn the herd the leader and regent is ";
		
		
		if(anList.get(0).getPackLeaderIdentifier().equals("Eldest Female"))   // check for king conditions
			if (females > 0)												       // make sure there is an female/male in the first place
				social += "the eldest female. Her name is "+eldestFemale.getName()+".";
			else                                                                         
				social += montyQuote;
		
		if(anList.get(0).getPackLeaderIdentifier().equals("Eldest Male"))   // check for king conditions
			if (males > 0)												       // make sure there is an female/male in the first place
				social += "the eldest male. His name is "+eldestMale.getName()+".";
			else                                                                         
				social += montyQuote;
		
		if(anList.get(0).getPackLeaderIdentifier().equals("Largest Female"))   // check for king conditions
			if (females > 0)												       // make sure there is an female/male in the first place
				social += "the Largest female. Her name is "+largestFemale.getName()+".";
			else                                                                         
				social += montyQuote;
		
		if(anList.get(0).getPackLeaderIdentifier().equals("Largest Male"))   // check for king conditions
			if (males > 0)												       // make sure there is an female/male in the first place
				social += "the Largest male. His name is "+largestMale.getName()+".";
			else                                                                         
				social += montyQuote;
		
		if(anList.get(0).getPackLeaderIdentifier().equals("Biggest Female"))   // check for king conditions
			if (females > 0)												       // make sure there is an female/male in the first place
				social += "the biggest female. Her name is "+biggestFemale.getName()+".";
			else                                                                         
				social += montyQuote;
		
		if(anList.get(0).getPackLeaderIdentifier().equals("biggest Male"))   // check for king conditions
			if (males > 0)												       // make sure there is an female/male in the first place
				social += "the biggest male. His name is "+biggestMale.getName()+".";
			else                                                                         
				social += montyQuote;

		printDialog(social);
		
	}
}

