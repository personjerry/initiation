import java.util.*;
import java.io.*;

public class SaveFile implements Serializable {
  Vector<Person> people;
  Village village;
  SaveFile(Initiation root) {
    //add some people
    people=new Vector<Person>();
    addPerson(new Person(root,true));
    addPerson(new Person(root,false));
    
    //make the village
    village=new Village(root);
  }
  private void addPerson(Person p) {
    people.add(p);
  }
  private int getPerson(String name) {
    name=WordGen.fCap(name);
    for(int i=0;i<people.size();i++) {
      Person p=(Person)people.get(i);
      if(p.name.equals(name)) {
        return i;
      }
    }
    return 0;
  }
  private void removePerson(int i) {
    people.remove(i);
  }
}