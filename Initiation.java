import java.util.*;
import java.io.*;

public class Initiation {
  Interface io;
  MT rand;
  SaveFile s;
  
  //Constants (None)
  
   //File Handling
  FileOutputStream fos;
  ObjectOutputStream out;
  FileInputStream fis;
  ObjectInputStream in;
  
  public static void main(String[] args) {
    new Initiation();
  }
  Initiation() {
    io=new Interface();
    rand=new MT();
    while(true){
      io.out("Would you like to create a new file, or load one? (new/load)");
      String user_input=io.nextLine();
      io.out("");
      if(user_input.equals("new")) {
        create();
        save("save.dat");
        io.out("File successfully created.");
        break;
      } else {
        if(load("save.dat")) {
          io.out("Load successful.");
          break;
        } else {
          io.out("Save file not found or corrupted.");
        }
      }
    }
    
    io.out("Welcome to Initiation.");
    io.out("//insert storyline here");
    boolean exit=false;
    while(!exit) {
      String c=io.nextLine();
      c=c.toLowerCase();
      String[] command=c.split(" ");
      
      if(command[0].equals("help")) {
        if(command.length==1) {
          io.out("Commands:");
          io.out("settask [person] [task]");
          io.out("day");
          io.out("resources");
          io.out("people");
          io.out("check [person]");
          io.out("help [command]");
          io.out("exit");
        } else if(command[1].equals("help")) {
          io.out("Shows help for that command.");
        } else if(command[1].equals("tasks")) {
          io.out("Assigns the task to the person.");
          io.out("Tasks are nothing, farm, build, rest, and mine.");
        } else if(command[1].equals("day")) {
          io.out("A day goes by; your people do their tasks.");
        } else if(command[1].equals("resources")) {
          io.out("Displays a list of resources you have.");
        } else if(command[1].equals("people")) {
          io.out("Displays a list of your people.");
        } else if(command[1].equals("check")) {
          io.out("Displays statistics for the person.");
        } else if(command[1].equals("exit")) {
          io.out("Exits; how stupid are you?");
        }
      } else if(command[0].equals("exit")) {
        exit=true;
      } else if(command[0].equals("resources")) {
        io.out("Resources:");
        io.out("Food: "+s.village.food);
        io.out("Wood: "+s.village.wood);
        io.out("Stone: "+s.village.stone);
      } else if(command[0].equals("settask")) {
        if(command.length==3) {
          //get the person's id
          int id=s.getPersonId(command[1]);
          if(id!=-1) {
            Task t=new Task(command[2]);
            s.people.get(id).setTask(t);
            io.out("Job set to: "+ t.getDisplayName());
          } else {
            io.out("Please enter a valid name.");
          }
        } else {
          io.out("Please use the form: settask [person] [task]");
        }
      } else if(command[0].equals("people")) {
        for(int i=0;i<s.getPop();i++) {
          io.out("-"+s.getPerson(i).name);
        }
      } else if(command[0].equals("check")) {
        if(command.length==2) {
          //get the person's id
          int id=s.getPersonId(command[1]);
          if(id!=-1) {
            s.getPerson(id).details();
          } else {
            io.out("Please enter a valid name.");
          }
        } else {
          io.out("Please use the form: check [person]");
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
      for(int i=0;i<s.getPop();i++) {
        s.getPerson(i).load(this);
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
  
  //END DAY FUNCTION
  void endDay() {
    //cycle through people, do jobs
    for(int i=0;i<s.getPop();i++) {
      Person p=s.getPerson(i);
      if(p.task.task.equals("farm")) {
        farm(p);
      }
    }
  }
  
  //JOB FUNCTIONS
  void farm(Person p) {
    
  }
}