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
    Object[] p=people.values().toArray();
    for(int i=0;i<p.length;i++) {
      ((Person)p[i]).details();
    }
  }
}