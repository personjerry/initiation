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
  Vector<Building> buildings;
  Village(Initiation root) {
    this.root=root;
    food=40;
    wood=10;
    stone=0;
    crop=0;
    iron=0;
    verbose=false; //I would always forget to change this and it would annoy me
    
    //add some people
    people=new Vector<Person>();
    addPerson(new Person(root,true));
    addPerson(new Person(root,false));
    
    //initialize buildings vector
    buildings=new Vector<Building>();
    //you start with four storage buildings of size 1 made of wood
    addBuilding(new Building(0,0,1));
    addBuilding(new Building(0,0,1));
    addBuilding(new Building(0,0,1));
    addBuilding(new Building(0,0,1));
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
  
  /* BUILDING FUNCTIONS */
  public void addBuilding(Building b) {
    buildings.add(b);
  }
  public int getBuildingId(Building b) {
    //note: buildings are not unique; this function finds the id of the first equivalant building
    for(int i=0;i<getBuildingNum();i++) {
      if(getBuilding(i).equals(b)) {
        return i;
      }
    }
    return -1;
  }
  public Building getBuilding(int id) {
    if(id<getBuildingNum()) {
      Building b=(Building)buildings.get(id);
      return b;
    }
    return null;
  }
  public int getBuildingNum() {
    return buildings.size();
  }
  public void removeBuilding(int id) {
    buildings.remove(id);
  }
  public int countBuildings(int type) {
    int count=0;
    for(int i=0;i<getBuildingNum();i++) {
      if(getBuilding(i).type==type)
        count++;
    }
    return count;
  }
}