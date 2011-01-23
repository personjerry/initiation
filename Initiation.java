import java.util.*;
import java.io.*;

public class Initiation {
  Display d;
  MT rand;
  SaveFile s;
  
  //Constants
  int JOBS=6;
  
   //File Handling
  FileOutputStream fos;
  ObjectOutputStream out;
  FileInputStream fis;
  ObjectInputStream in;
  
  public static void main(String[] args) {
    new Initiation();
  }
  Initiation() {
    d=new Display();
    rand=new MT();
    
    d.out("Would you like to create a new file, or load one? (new/load)");
    String user_input=d.nextLine();
    if(user_input.equals("new")) {
      create();
      save("save.dat");
      d.out("File successfully created.");
    } else {
      if(load("save.dat")) {
        d.out("Load successful.");
      } else {
        d.out("Save file not found or corrupted. A new one has been created.");
        create();
      }
    }
    
    d.out("Welcome to Initiation.");
    d.out("//insert storyline here");
    for(int i=0;i<s.people.size();i++) {
      Person p=(Person)s.people.get(i);
      p.details();
      if(i!=s.people.size()-1)
        d.out("-----------------------");
    }
    boolean exit=false;
    while(!exit) {
      String c=d.nextLine();
      c=c.toLowerCase();
      String[] command=c.split(" ");
      
      if(command[0].equals("help")) {
        if(command.length==1) {
          d.out("Commands:");
          d.out("settask [person] [task]-assigns the task to the person");
          d.out("day-a day goes by; your people do their tasks");
          d.out("resources-displays a list of resources you have");
          d.out("help [command]-shows help for that command");
          d.out("exit-exits; how stupid are you?");
        } else if(command[1].equals("help")) {
          d.out("you're asking how to use a command that you just used");
        } else if(command[1].equals("tasks")) {
          d.out("not yet implemented, check back later");
        } else if(command[1].equals("day")) {
          d.out("not yet implemented, check back later");
        } else if(command[1].equals("resources")) {
          d.out("displays a list of collected resources");
        } else if(command[1].equals("exit")) {
          d.out("it exits");
        }
      } else if(command[0].equals("exit")) {
        exit=true;
      } else if(command[0].equals("resources")) {
        d.out("Resources:");
        d.out("Food: "+s.village.food);
        d.out("Wood: "+s.village.wood);
        d.out("Stone: "+s.village.stone);
      } else if(command[0].equals("settask")) {
        if(command.length==3) {
          //get the person's id
          int id=s.getPerson(command[1]);
          if(id!=-1) {
            int task=-1;
            for(int i=0;i<JOBS;i++) {
              if(Person.getTaskName(i).toLowerCase().equals(command[2])) {
                task=i;
                break;
              }
            }
            if(task==-1) {
              d.out("Task is not valid. Setting task to 'Nothing'.");
              task=0;
            }
            s.people.get(id).setTask(task);
            d.out("Job set to: "+Person.getTaskName(task));
          } else {
            d.out("Please enter a valid name.");
          }
        } else {
          d.out("Please use the form: settask [person] [task]");
        }
      }
    }
    save("save.dat");
  }
  void create() {
    s=new SaveFile(this);
  }
  boolean load(String fn) {
    try {
      fis = new FileInputStream(fn);
      in = new ObjectInputStream(fis);
      s = (SaveFile)in.readObject();
      in.close();
      
      //call load functions of loaded classes (because root isn't stored)
      for(int i=0;i<s.people.size();i++) {
        Person p=(Person)s.people.get(i);
        p.load(this);
      }
      s.village.load(this);
      
      return true;
    } catch(IOException ex) {
      //ex.printStackTrace();
      return false;
    } catch (ClassNotFoundException ex) {
      //ex.printStackTrace();
      return false;
    }
  }
  void save(String fn) {
    try {
      fos=new FileOutputStream(fn);
      out=new ObjectOutputStream(fos);
      out.writeObject(s);
      out.close();
    } catch(IOException ex) {
      ex.printStackTrace();
    }
  }
}