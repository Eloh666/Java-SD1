import java.util.ArrayList;
import java.util.Arrays;

public class ElementsLibrary {
	
	private ArrayList<String> names;
	private ArrayList<String> locations;
	
	public ElementsLibrary()
	{
		this.names = new ArrayList<>();
		names.addAll(Arrays.asList("Luke Skywalker", "Darth Vader", "Obi-Wan Kenobi", "Darth Sidious", "Darth Maul", // Star Wars
			"Yoda", "Revan", "Exar Kun", "Marka Ragnos",
		   "Gandalf", "Morgoth", "Sauron", "Ancalagon the Black", "Saruman", "Aragorn",   // Tolkien
		   "Frodo", "Legolas", "Elrond","Galadriel","Feanor","Bilbo","Arwen",
		   "Kirkegard", "Hegel", "Kant", "Feuerbach", "Nietzsche", "Plato", "Aristotle", "De'Cartes", // philosophers 
		   "Paul Muad'Dib","Chani","Elric of Melnibone'","Oda Nobunaga", "Macchiavelli", "Anomander Rake", // others
		   "Django... the D is silent","Marcellus Wallance","Jaqen H'ghar","eh... can't remember his name"
		));
		
		this.locations = new ArrayList<>();
		locations.addAll(Arrays.asList("in the forgotten city of R'lyeh", "inside the Matrix", "in a world between worlds", "in a dream",
				 "in the city of Sigil", "in a nightmare", "in...well I honestly don't remember", "well.. you wouldn't know the place anyways!"));
	}

	public ArrayList<String> getNames() {
		return names;
	}

	public void setNames(ArrayList<String> names) {
		this.names = names;
	}

	public ArrayList<String> getLocations() {
		return locations;
	}

	public void setLocations(ArrayList<String> locations) {
		this.locations = locations;
	}
	
}
