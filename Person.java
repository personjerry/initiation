import java.io.*;

public class Person implements Serializable {
  transient Initiation root;
  transient WordGen wg;
  
  public String name;
  public int task;
  public boolean male;
  public int age;
  public int attr_str;
  public int attr_int;
  public Person(Initiation root) { 
    this(root,root.rand.random()%2==0);
    /*if(root.rand.random()%2==0)
      male=true;
    else
      male=false;*/
  }
  public Person(Initiation root,boolean male) {
    this.root=root;
    this.male=male;
    
    wg=new WordGen();
    name=WordGen.fCap(wg.format(wg.ss(root.rand.random()%2+2)));
    
    task=0; //Nothing
    
    age=20;
    attr_str=root.rand.random()%10+1;
    //attr_int=root.rand.random()%10+1;
    attr_int=11-attr_str; //so that you don't get overly powerful, or weak characters
  }
  public void details() {
    root.d.out("Name: "+name);
    root.d.out("Task: "+getTaskName(task));
    if(male)
      root.d.out("Male");
    else
      root.d.out("Female");
    root.d.out("Age: "+age);
    root.d.out("Strength: "+attr_str);
    root.d.out("Intelligence: "+attr_int);
  }
  public void load(Initiation root) {
    this.root=root;
    wg=new WordGen();
  }
  public static String getTaskName(int i) {
    switch(i) {
      case 0:
        return "Nothing";
      case 1:
        return "Farming";
      case 2:
        return "Building";
      case 3:
        return "Resting";
      case 4:
        return "Mining";
      case 5:
        return "Getting Wood"; //heh heh
    }
    return null;
  }
  public void setTask(int i) {
    task=i;
  }
}
