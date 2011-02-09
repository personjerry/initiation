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
        int crop=(attr_int-root.rand.random()%10>3)?(attr_str-root.rand.random()%10+2):0;
        if(crop>0) { //success?
          if(crop>1) {
            root.io.out(name+" farmed, growing "+crop+" crops.");
          } else {
            root.io.out(name+" farmed, growing 1 crop.");
          }
          root.s.village.crop+=crop;
        } else {
          root.io.out(name+" farmed, but couldn't grow any crops.");
        }
      }
      if(tn.equals("mine")) {
        int stone=(attr_str-root.rand.random()%10>3)?(attr_str-root.rand.random()%10+2):0;
        if(stone>0) { //success?
          if(stone>1) {
            root.io.out(name+" mined, getting "+stone+" units of stone.");
          } else {
            root.io.out(name+" mined, getting 1 unit of stone.");
          }
          root.s.village.stone+=stone;
          int iron=0;
          for(int i=0;i<stone;i++) {
            if(root.rand.random()%10==0) {
              iron++;
            }
          }
          if(iron>0) {
            if(iron>1) {
              root.io.out("While mining, "+name+" also found "+iron+" units of iron.");
            } else {
              root.io.out("While mining, "+name+" also found 1 unit of iron.");
            }
            root.s.village.iron+=iron;
          }
        } else {
          if(root.rand.random()%25!=0) {
            root.io.out(name+" mined, but didn't get any stone.");
          } else {
            root.io.out(name+" drank instead of mining, but didn't get stoned."); //EASTER EGG!!!!11oneone1
          }
        }
      }
      if(tn.equals("trainint")) {
        if(root.rand.random()%10>3) {
          root.io.out(name+" got one additional intelligence point.");
          attr_int+=1;
        } else {
          root.io.out(name+" trained intelligence, but didn't get any smarter.");
        }
      }
      if(tn.equals("trainstr")) {
        if(root.rand.random()%10>3) {
          root.io.out(name+" got one additional strength point.");
          attr_str+=1;
        } else {
          root.io.out(name+" worked out, but didn't get any stronger.");
        }
      }
    }
  }
  public void live() {
    int temp =(int)Math.ceil(age/10.0);
    root.io.out(name+" ate "+temp+" food.");
    root.s.village.food-=temp;    
  }
}
