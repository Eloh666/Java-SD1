import java.io.*;
import java.util.*;

public class BBC_TreeMap {
  public static void main(String[] argv) throws Exception{
    BufferedReader br = new BufferedReader(new FileReader("bbc.txt"));
    TreeMap<String,Country> world = new TreeMap<String,Country>();
    for(String s=br.readLine();s!=null;s=br.readLine()){
      Country c = new Country(s);
      world.put(c.name,c);
    }
    br.close();
    System.out.println(world.get("France"));
  }
}
