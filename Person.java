import java.io.*;

public class Person implements Serializable {
  transient Initiation root;
  transient WordGen wg;
  
  public String name;
  public String task;
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
    
    task="nothing"; //Nothing
    
    age=root.rand.random()%10+18;
    attr_str=root.rand.random()%10+1;
    attr_int=root.rand.random()%10+1;
    //attr_int=11-attr_str; //so that you don't get overly powerful, or weak characters -> However in real life there are people who are completely useless.
  }
  public void details() {
    root.io.out("Name: "+name);
    root.io.out("Task: "+getTask());
    if(male)
      root.io.out("Male");
    else
      root.io.out("Female");
    root.io.out("Age: "+age);
    root.io.out("Strength: "+attr_str);
    root.io.out("Intelligence: "+attr_int);
  }
  public void load(Initiation root) {
    this.root=root;
    wg=new WordGen();
  }
  public static String getTaskName(String i) { //TODO: Get rid of the need for this, just use the task strings.
    if(i.equals("nothing"))
        return "Nothing";
    else if(i.equals("farming"))
        return "Farming";
    else if(i.equals("building"))
        return "Building";
    else if(i.equals("resting"))
        return "Resting";
    else if(i.equals("mining"))
        return "Mining";
    return "Nothing (Not Set)";
  }
    
  public String getTask() {
    return task;
  }
  public void setTask(String i) {
    task=i;
  }
}
