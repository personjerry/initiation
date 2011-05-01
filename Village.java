import java.io.*;
import java.util.*;

public class Village implements Serializable {
  transient Initiation root;
  int food;
  int wood;
  int stone;
  int crop;
  int iron;
  boolean verbose;
  Vector<Person> people;
  Village(Initiation root) {
    this.root=root;
    food=20;
    wood=0;
    stone=0;
    crop=0;
    iron=0;
    verbose=false; //I would always forget to change this and it would annoy me
    
    //add some people
    people=new Vector<Person>();
    addPerson(new Person(root,true));
    addPerson(new Person(root,false));
  }
  public void load(Initiation root) {
    this.root=root;
  }
  
  /* PEOPLE FUNCTION */
  public void addPerson(Person p) {
    people.add(p);
  }
  public int getPersonId(String name) {
    name=WordGen.fCap(name);
    for(int i=0;i<getPop();i++) {
      if(getPerson(i).name.equals(name)) {
        return i;
      }
    }
    return -1;
  }
  public Person getPerson(int id) {
    if(id<getPop()) {
      Person p=(Person)people.get(id);
      return p;
    }
    return null;
  }
  public int getPop() {
    return people.size();
  }
  public void removePerson(int i) {
    people.remove(i);
  }
}