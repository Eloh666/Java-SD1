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
				makeObjects(animals,mode);
			}
			else
			{
				printDialog("No option selected, please try again!"); // the user closed the window instead
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
	
	public static void makeObjects(ArrayList<String> animals, int mode)
	{	
		ElementsLibrary lib= new ElementsLibrary();		
		Random gen = new Random();
		int generated = -1;
		String genders[] = {"Male", "Female"}; 
		ArrayList<Animal> animalsList = new ArrayList<>();	  // the list of animal classes that will be used by the Narrator/social methods	
		for(String animal : animals)
		{
			Animal newAnimal = createObject(animal);
			
			generated = gen.nextInt(lib.getNames().size());      // set unique name
			newAnimal.setName(lib.getNames().get(generated));
			lib.getNames().remove(generated);
			animalsList.add(newAnimal);
			
			newAnimal.setGender(genders[gen.nextInt(2)]);       // gender
			
			newAnimal.setAge(0);
			
			newAnimal.setWeight(0);
			
			newAnimal.setHeight(0);
		}
	}
	
	static Animal createObject(String className) {
	      Object object = null;
	      try 
	      {
	          Class classDefinition = Class.forName(className);
	          object = classDefinition.newInstance();
	      } catch (InstantiationException e) 
	      {
	          object = new Animal();
	      } catch (IllegalAccessException e) 
	      {
	    	  object = new Animal();
	      } catch (ClassNotFoundException e) 
	      {
	          object = new Animal();
	      }
	      return (Animal) object;                 // TODO REALLY not sure if I should do this
	   }
}

