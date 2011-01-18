public class Person {
  Initiation root;
  public String name;
  public String task;
  public boolean male;
  public int age;
  public int attr_str;
  public int attr_int;
  WordGen wg;
  public Person(Initiation root) { 
    this.root=root;
    wg=new WordGen();
    name=wg.fCap(wg.format(wg.ss(root.rand.random()%2+2)));
    task="Nothing";
    if(root.rand.random()%2==0)
      male=true;
    else
      male=false;
    attr_str=root.rand.random()%10+1;
    //attr_int=root.rand.random()%10+1;
    attr_int=15-attr_str; //so that you don't get overly powerful, or weak characters
  }
  public void details() {
    root.d.out("Name: "+name);
    root.d.out("Task: "+task);
    if(male)
      root.d.out("Male");
    else
      root.d.out("Female");
    root.d.out("Age: "+age);
    root.d.out("Strength: "+attr_str);
    root.d.out("Intelligence: "+attr_int);
  }
}
