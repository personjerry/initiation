import java.io.*;

public class Person implements Serializable {
  transient Initiation root;
  transient WordGen wg;
  
  public String name;
  public Task task;
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
    name=WordGen.fCap(wg.format(wg.ss(root.rand.random()%2+1)));
    
    task=new Task("nothing");
    
    age=root.rand.random()%10+18;
    attr_str=root.rand.random()%10+1;
    attr_int=root.rand.random()%10+1;
    //attr_int=11-attr_str; //so that you don't get overly powerful, or weak characters -> However in real life there are people who are completely useless.
  }
  public void details() {
    root.io.out("Name: "+name);
    root.io.out("Task: "+getTask().getDisplayName());
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
  public Task getTask() {
    return task;
  }
  public String getTaskName() {
    return task.task;
  }
  public void setTask(Task t) {
    task=t;
  }
  public void doTask() {
    boolean ableToWork=true; //no debilitating conditions yet
    if(ableToWork) {
      String tn=getTaskName();
      if(tn.equals("farm")) {
        int food=attr_int-root.rand.random()%10+2;
        if(food>0) { //success?
          if(food>1) {
            root.io.out(name+" farmed, getting "+food+" pieces of food.");
          } else {
            root.io.out(name+" farmed, getting 1 piece of food.");
          }
          root.s.village.food+=food;
        } else {
          root.io.out(name+" farmed, but didn't manage to get any food.");
        }
      }
    }
  }
}
