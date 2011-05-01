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
          io.out("play");
          io.out("resources");
          io.out("people");
          io.out("check [person]");
          io.out("help [command]");
          io.out("verbose");
          io.out("exit");
        } else if(command[1].equals("help")) {
          io.out("Shows help for that command.");
        } else if(command[1].equals("tasks") ||command[1].equals("settask") ) {
          io.out("Assigns the task to the person.");
          io.out("Tasks are nothing, farm, build, train, rest, and mine.");
          io.out("Some commands like build and train take another parameter,");
          io.out("consult help.txt for more info.");
        } else if(command[1].equals("play")) {
          io.out("A day goes by; your people do their tasks.");
        } else if(command[1].equals("resources")) {
          io.out("Displays a list of resources you have.");
        } else if(command[1].equals("people")) {
          io.out("Displays a list of your people.");
        } else if(command[1].equals("check")) {
          io.out("Displays statistics for the person.");
        } else if(command[1].equals("verbose")) {
          io.out("Toggles some text. Check resources to see if it is on.");
        } else if(command[1].equals("exit")) {
          io.out("Exits the program.");
        } else {
          io.out("Unknown command, type \'help\' for commands.");
        }
      } else if(command[0].equals("exit")) {
        exit=true;
      } else if(command[0].equals("resources")) {
        io.out("Resources:");
        io.out("Food: "+s.village.food);
        io.out("Wood: "+s.village.wood);
        io.out("Stone: "+s.village.stone);
        io.out("Crop: "+s.village.crop);
        io.out("Buildings:");
        io.out("-Storage: "+s.village.countBuildings(0));
        io.out("-Walls: "+s.village.countBuildings(1));
        io.out("Verbose: "+s.village.verbose);
      } else if(command[0].equals("settask") || command[0].equals("assign")  ) { //assign now works as settask
        if(command.length==3||command.length==4) {
          //get the person's id
          int id=s.village.getPersonId(command[1]);
          if(id!=-1) {
            Task t=new Task(command[2]);
            if(command.length==4) t=new Task(command[2],command[3]);
            s.village.people.get(id).setTask(t);
            io.out("Job set to: "+ t.getDisplayName());
          } else {
            io.out("Please enter a valid name.");
          }
        } else {
          io.out("Please use the form: settask [person] [task]");
        }
      } else if(command[0].equals("people")) {
        for(int i=0;i<s.village.getPop();i++) {
          io.out("-"+s.village.getPerson(i).name);
        }
      } else if(command[0].equals("play") || command[0].equals("p")  ) { //p now works as play
        int idle = 0;
        for(int i=0;i<s.village.people.size();i++) {
          Person p=s.village.getPerson(i);
          if(p.getTaskName().equals("nothing"))
          {
            io.out("Warning: "+p.name+" is doing nothing!");
            idle++;
          }
        }
        boolean day = true;
        if(idle>0)
        {
          io.out(""+idle+" person(s) are going to do nothing. Continue? (yes/no)");
          String prompt = io.nextLine();
          if (prompt.equals("yes"));
          else if(prompt.equals("no")) {
            day = false;
          }
          else {
            io.out("Unknown command, defaulted to \'no\'.");
            day = false;
          }
        }
        if(day) {
          endDay();
        }
      } else if(command[0].equals("check")) {
        if(command.length==2) {
          //get the person's id
          int id=s.village.getPersonId(command[1]);
          if(id!=-1) {
            s.village.getPerson(id).details();
          } else {
            io.out("Please enter a valid name.");
          }
        } else {
          io.out("Please use the form: check [person]");
        }
      } else if(command[0].equals("verbose")) {
        io.out("Verbose is now "+!s.village.verbose);
        s.village.verbose=!s.village.verbose;
      } else {
        io.out("Unknown command, type \'help\' for commands.");
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
      for(int i=0;i<s.village.getPop();i++) {
        s.village.getPerson(i).load(this);
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
    for(int i=0;i<s.village.getPop();i++) {
      Person p=s.village.getPerson(i);
      p.doTask();
      p.live();
      if(s.village.verbose)
      {
        io.out("Press enter to continue...");
        if(i==0)
        {
          io.out("(You can toggle this prompt by typing \'verbose\' at the menu)");
        }
        io.pressEnter();
      }
    }
    int tempcrop = 0;
    int tempfood = 0;
    for(int i=0;i<s.village.crop;i++) {
      if(rand.random()%20<2)
      {
        tempcrop++;
        tempfood+=rand.random()%4+2;
      }
    }
    if(tempcrop>0) {
      io.out(""+tempcrop+" crops matured, yielding "+tempfood+" food.");
      s.village.food+=tempfood;
      s.village.crop-=tempcrop;
    }
    
    //Rot food without storage
    int amt_storage=s.village.countBuildings(0)*10;
    if(s.village.food>amt_storage) {
      io.out((s.village.food-amt_storage)+" food rotted, leaving you with "+(amt_storage)+" food. Build more storage.");
      s.village.food=amt_storage;
    }
  }
}
