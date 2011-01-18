import java.util.*;
public class Initiation {
  Display d;
  MT rand;
  Hashtable people;
  
  public static void main(String[] args) {
    new Initiation();
  }
  Initiation() {
    d=new Display();
    rand=new MT();
    
    //add some people
    people=new Hashtable();
    Person temp = new Person(this);
    people.put(temp.name, temp);
    temp = new Person(this);
    people.put(temp.name, temp);
    
    d.out("Welcome to Initiation.");
    d.out("//insert storyline here");
    //this is ugly, figure out a better way to do it (array of people, hash of names? how slow is searching through the array?)
    Object[] p=people.values().toArray();
    for(int i=0;i<p.length;i++) {
      ((Person)p[i]).details();
      if(i!=p.length-1)
        d.out("-----------------------");
    }
  }
}